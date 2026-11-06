package com.sample.tmdb.data.repository.tvshow.detail

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseDetailRepositoryTest
import com.sample.tmdb.data.response.ImagesResponse
import com.sample.tmdb.data.response.NetworkCreditWrapper
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TvDetailResponse
import com.sample.tmdb.data.source.entity.TVShowDetailEntity
import com.sample.tmdb.data.source.local.TVShowDetailDao
import com.sample.tmdb.domain.model.TVShowDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TVShowDetailRepositoryTest : BaseDetailRepositoryTest<TVShowDetails>() {
    @Mock
    private lateinit var api: TVShowService

    @Mock
    private lateinit var dao: TVShowDetailDao

    override fun initRepository() {
        repository = TVShowDetailRepository(api, dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        var dbEntity: TVShowDetailEntity? = null
        whenever(dao.getTVShowDetail(anyInt())).thenAnswer { dbEntity }
        doAnswer { invocation ->
            dbEntity = invocation.getArgument(0)
            null
        }.whenever(dao).insert(any())

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
        `when`(api.fetchSimilarTVSeries(anyInt(), eq(1))).thenReturn(NetworkTMDbWrapper(emptyList()))
    }

    override fun mockFailApiResponse() = runTest {
        whenever(dao.getTVShowDetail(anyInt())).thenReturn(null)
        `when`(api.fetchTVSeriesDetail(anyInt())).thenThrow(RuntimeException())
    }
}
