package com.sample.tmdb.data.paging.tvshow

import com.sample.tmdb.data.paging.BaseTVSeriesPagingSourceTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TVShowResponse
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.navigation.SortType
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimilarTVSeriesPagingSourceTest : BaseTVSeriesPagingSourceTest() {

    override suspend fun getApiCall(): NetworkTMDbWrapper<TVShowResponse> = api.fetchSimilarTVSeries(1, 1)

    override fun getPagingDataSource(): BasePagingSource<TVShow> =
        TVSeriesPagingSource(context, api, SortType.SIMILAR, 1)
}
