package com.sample.tmdb.data.repository.tvshow

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TVShowResponse
import com.sample.tmdb.data.source.entity.TVShowFeedEntity
import com.sample.tmdb.data.source.local.TVShowFeedDao
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.GOOGLE_PLAY_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowFeedRepositoryTest : BaseFeedRepositoryTest<TVShow>() {
    @Mock
    private lateinit var api: TVShowService

    @Mock
    private lateinit var dao: TVShowFeedDao

    override fun initRepository() {
        repository = TVShowFeedRepository(context, Dispatchers.Main, api, dao)
    }

    override fun mockApiResponse() = runTest {
        var dbEntities = emptyList<TVShowFeedEntity>()
        `when`(dao.getFeed()).thenAnswer { dbEntities }
        doAnswer { invocation ->
            dbEntities = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsert(anyList())

        val tvShow = TVShowResponse(1, "overview", "2024-01-01", null, null, "name", 8.0, 100)
        val wrapper = NetworkTMDbWrapper(listOf(tvShow))

        `when`(api.trendingTVSeries()).thenReturn(wrapper)
        `when`(api.popularTVSeries()).thenReturn(wrapper)
        `when`(api.airingTodayTVSeries()).thenReturn(wrapper)
        `when`(api.onTheAirTVSeries()).thenReturn(wrapper)
        `when`(api.topRatedTVSeries()).thenReturn(wrapper)
        `when`(api.discoverTVSeries()).thenReturn(wrapper)
        `when`(api.providerTVSeries(APPLE_TV_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerTVSeries(NETFLIX_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerTVSeries(AMAZON_PRIME_PROVIDER_ID)).thenReturn(wrapper)
        `when`(api.providerTVSeries(GOOGLE_PLAY_PROVIDER_ID)).thenReturn(wrapper)
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getFeed()).thenReturn(emptyList())
        `when`(api.trendingTVSeries()).thenThrow(RuntimeException())
    }
}
