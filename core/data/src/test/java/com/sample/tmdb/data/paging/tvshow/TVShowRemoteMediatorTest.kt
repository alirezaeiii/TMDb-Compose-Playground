package com.sample.tmdb.data.paging.tvshow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BaseRemoteMediatorTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TVShowResponse
import com.sample.tmdb.data.source.entity.TVShowPagingEntity
import com.sample.tmdb.data.source.local.TVShowPagingDao
import com.sample.tmdb.data.source.local.TVShowRemoteKeyDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.SortType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class TVShowRemoteMediatorTest : BaseRemoteMediatorTest() {

    private val api: TVShowService = mockk()
    private val tvShowPagingDao: TVShowPagingDao = mockk(relaxed = true)
    private val tvShowRemoteKeyDao: TVShowRemoteKeyDao = mockk(relaxed = true)

    private val tvShow1 = TVShowResponse(
        1,
        "overview1",
        null,
        null,
        null,
        "name1",
        1.1,
        1,
    )
    private val tvShow2 = TVShowResponse(
        2,
        "overview2",
        null,
        null,
        null,
        "name2",
        1.1,
        1,
    )

    @Before
    fun setup() {
        every { database.tvShowPagingDao } returns tvShowPagingDao
        every { database.tvShowRemoteKeyDao } returns tvShowRemoteKeyDao
    }

    private fun mockApiResponse(sortType: SortType) {
        coEvery { getApiCall(sortType) } returns NetworkTMDbWrapper(listOf(tvShow1, tvShow2))
    }

    private suspend fun getApiCall(sortType: SortType): NetworkTMDbWrapper<TVShowResponse> = when (sortType) {
        SortType.TRENDING -> api.trendingTVSeries(1)
        SortType.DISCOVER -> api.discoverTVSeries(1)
        SortType.MOST_POPULAR -> api.popularTVSeries(1)
        SortType.NOW_PLAYING -> api.airingTodayTVSeries(1)
        SortType.UPCOMING -> api.onTheAirTVSeries(1)
        SortType.HIGHEST_RATED -> api.topRatedTVSeries(1)
        SortType.SIMILAR -> api.fetchSimilarTVSeries(1, 1)
        SortType.APPLE_TV -> api.providerTVSeries(APPLE_TV_PROVIDER_ID, 1)
        SortType.NETFLIX -> api.providerTVSeries(NETFLIX_PROVIDER_ID, 1)
        SortType.AMAZON_PRIME -> api.providerTVSeries(AMAZON_PRIME_PROVIDER_ID, 1)
        SortType.GOOGLE_PLAY -> api.providerTVSeries(GOOGLE_PLAY_PROVIDER_ID, 1)
    }

    @Test
    fun refreshTVShowRemoteMediatorSuccess() = runTest {
        SortType.entries.forEach { sortType ->
            mockApiResponse(sortType)
            val mediator = TVShowRemoteMediator(
                database = database,
                tvShowApi = api,
                sortType = sortType,
                tvShowId = if (sortType == SortType.SIMILAR) 1 else null,
                context = context,
            )
            val pagingState = PagingState<Int, TVShowPagingEntity>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(20),
                leadingPlaceholderCount = 0,
            )
            val result = mediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse(result.endOfPaginationReached)
        }
    }
}
