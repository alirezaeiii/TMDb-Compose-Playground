package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.tvshow.TVShowFeedRepository
import com.sample.tmdb.data.response.TMDbWrapper
import com.sample.tmdb.domain.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowFeedRepositoryTest: BaseFeedRepositoryTest<TVShow>() {

    @Mock
    private lateinit var api: TVShowService

    override fun initRepository() {
        repository = TVShowFeedRepository(context, Dispatchers.Main, api)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.trendingTVSeries()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.popularTVSeries()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.airingTodayTVSeries()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.onTheAirTVSeries()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.topRatedTVSeries()).thenReturn(TMDbWrapper(emptyList()))
        `when`(api.discoverTVSeries()).thenReturn(TMDbWrapper(emptyList()))
    }
}