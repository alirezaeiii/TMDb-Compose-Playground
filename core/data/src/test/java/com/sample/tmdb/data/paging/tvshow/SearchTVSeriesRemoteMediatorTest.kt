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
import com.sample.tmdb.data.source.entity.SearchTVShowPagingEntity
import com.sample.tmdb.data.source.local.SearchTVShowPagingDao
import com.sample.tmdb.data.source.local.SearchTVShowRemoteKeyDao
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class SearchTVSeriesRemoteMediatorTest : BaseRemoteMediatorTest() {

    private val api: TVShowService = mockk()
    private val searchTVShowPagingDao: SearchTVShowPagingDao = mockk(relaxed = true)
    private val searchTVShowRemoteKeyDao: SearchTVShowRemoteKeyDao = mockk(relaxed = true)

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
        every { database.searchTVShowPagingDao } returns searchTVShowPagingDao
        every { database.searchTVShowRemoteKeyDao } returns searchTVShowRemoteKeyDao
    }

    @Test
    fun refreshSearchTVSeriesRemoteMediatorSuccess() = runTest {
        val query = "query"
        coEvery { api.searchTVSeries(1, query) } returns NetworkTMDbWrapper(listOf(tvShow1, tvShow2))
        val mediator = SearchTVSeriesRemoteMediator(
            database = database,
            tvShowApi = api,
            query = query,
            context = context,
        )
        val pagingState = PagingState<Int, SearchTVShowPagingEntity>(
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
