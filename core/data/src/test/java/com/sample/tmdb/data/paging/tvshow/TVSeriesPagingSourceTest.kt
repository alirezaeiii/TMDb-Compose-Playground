package com.sample.tmdb.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BasePagingSourceTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TVShowResponse
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.navigation.model.SortType
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVSeriesPagingSourceTest : BasePagingSourceTest<TVShow, TVShowResponse>() {

    @Mock
    private lateinit var api: TVShowService

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

    override fun mockApiResponse(sortType: SortType) = runTest {
        `when`(getApiCall(sortType)).thenReturn(NetworkTMDbWrapper(listOf(tvShow1, tvShow2)))
    }

    override suspend fun getApiCall(sortType: SortType): NetworkTMDbWrapper<TVShowResponse> = when (sortType) {
        SortType.TRENDING -> api.trendingTVSeries(1)
        SortType.DISCOVER -> api.discoverTVSeries(1)
        SortType.MOST_POPULAR -> api.popularTVSeries(1)
        SortType.NOW_PLAYING -> api.airingTodayTVSeries(1)
        SortType.UPCOMING -> api.onTheAirTVSeries(1)
        SortType.HIGHEST_RATED -> api.topRatedTVSeries(1)
        SortType.SIMILAR -> api.fetchSimilarTVSeries(1, 1)
    }

    override fun getPagingDataSource(sortType: SortType): BasePagingSource<TVShow> = TVSeriesPagingSource(
        context,
        api,
        sortType,
        tvShowId = if (sortType == SortType.SIMILAR) 1 else null,
    )

    @Test
    fun pageKeyedShowPagingSource() = runTest {
        SortType.entries.forEach { sortType ->
            mockApiResponse(sortType)
            assertEquals(
                expected = Page(
                    data = listOf(tvShow1, tvShow2).asTVShowDomainModel(),
                    prevKey = null,
                    nextKey = 2,
                ),
                actual = getPagingDataSource(sortType).load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false,
                    ),
                ),
            )
        }
    }
}
