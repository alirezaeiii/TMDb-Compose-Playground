package com.sample.tmdb.data.repository.tvshow.detail

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseDetailRepositoryTest
import com.sample.tmdb.data.response.ImagesResponse
import com.sample.tmdb.data.response.NetworkCreditWrapper
import com.sample.tmdb.data.response.TMDbWrapper
import com.sample.tmdb.data.response.TvDetailResponse
import com.sample.tmdb.domain.model.TvDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowDetailRepositoryTest : BaseDetailRepositoryTest<TvDetails>() {
    @Mock
    private lateinit var api: TVShowService

    override fun initRepository() {
        repository = TVShowDetailRepository(api, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.fetchTVSeriesDetail(anyInt())).thenReturn(
            TvDetailResponse(
                BACKDROP_PATH,
                emptyList(),
                HOMEPAGE,
                ID,
                ORIGINAL_LANGUAGE,
                ORIGINAL_TITLE,
                OVERVIEW,
                POPULARITY,
                POSTER_PATH,
                RELEASE_DATE,
                emptyList(),
                STATUS,
                TAG_LINE,
                TITLE,
                VOTE_AVERAGE,
                VOTE_COUNT,
            ),
        )
        `when`(api.fetchTVSeriesCredit(anyInt())).thenReturn(NetworkCreditWrapper(emptyList(), emptyList()))
        `when`(api.fetchImages(anyInt())).thenReturn(ImagesResponse(emptyList(), ID, emptyList()))
        `when`(api.fetchSimilarTVSeries(anyInt())).thenReturn(TMDbWrapper(emptyList()))
    }
}
