package com.sample.tmdb.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.paging.BaseSearchPagingSourceTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TVShowResponse
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchTVSeriesPagingSourceTest : BaseSearchPagingSourceTest<TVShow, TVShowResponse>() {

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

    override fun mockApiResponse() = runTest {
        `when`(getApiCall()).thenReturn(NetworkTMDbWrapper(listOf(tvShow1, tvShow2)))
    }

    @Test
    fun pageKeyedShowPagingSource() = runTest {
        mockApiResponse()
        assertEquals(
            expected = Page(
                data = listOf(tvShow1, tvShow2).asTVShowDomainModel(),
                prevKey = null,
                nextKey = 2,
            ),
            actual = getPagingDataSource().load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false,
                ),
            ),
        )
    }

    override suspend fun getApiCall(): NetworkTMDbWrapper<TVShowResponse> = api.searchTVSeries(1, "")

    override fun getPagingDataSource(): BasePagingSource<TVShow> = SearchTVSeriesPagingSource(context, api, "")
}
