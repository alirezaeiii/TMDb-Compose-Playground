package com.sample.tmdb.data.repository.tvshow

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.GenresDTO
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.eq

@RunWith(MockitoJUnitRunner.Silent::class)
class TVShowFeedRepositoryTest : BaseFeedRepositoryTest<TVShow>() {
    @Mock
    private lateinit var api: TVShowService

    @Mock
    private lateinit var dao: TVShowFeedDao

    override fun initRepository() {
        repository = TVShowFeedRepository(context, Dispatchers.Main, api, dao)
    }

    override fun mockApiResponse() = runTest {
        var dbGenres = emptyList<com.sample.tmdb.data.source.entity.TVShowGenreEntity>()
        `when`(dao.getGenres()).thenAnswer { dbGenres }
        doAnswer { invocation ->
            dbGenres = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsertGenres(any())

        var dbEntities = emptyList<TVShowFeedEntity>()
        `when`(dao.getFeed(anyString())).thenAnswer { dbEntities }
        doAnswer { invocation ->
            dbEntities = invocation.getArgument(1)
            null
        }.`when`(dao).deleteAndInsert(anyString(), any())

        `when`(api.fetchGenres()).thenReturn(GenresDTO(listOf(com.sample.tmdb.data.response.GenreDTO(1, "Action"))))

        val tvShow = TVShowResponse(1, "overview", "2024-01-01", null, null, "name", 8.0, 100)
        val wrapper = NetworkTMDbWrapper(listOf(tvShow))

        `when`(api.trendingTVSeries(anyInt())).thenReturn(wrapper)
        `when`(api.popularTVSeries(anyInt())).thenReturn(wrapper)
        `when`(api.airingTodayTVSeries(anyInt())).thenReturn(wrapper)
        `when`(api.onTheAirTVSeries(anyInt())).thenReturn(wrapper)
        `when`(api.topRatedTVSeries(anyInt())).thenReturn(wrapper)
        `when`(api.discoverTVSeries(anyInt(), anyOrNull())).thenReturn(wrapper)
        `when`(api.providerTVSeries(eq(APPLE_TV_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
        `when`(api.providerTVSeries(eq(NETFLIX_PROVIDER_ID), anyInt(), anyOrNull(), anyString())).thenReturn(wrapper)
        `when`(
            api.providerTVSeries(eq(AMAZON_PRIME_PROVIDER_ID), anyInt(), anyOrNull(), anyString()),
        ).thenReturn(wrapper)
        `when`(
            api.providerTVSeries(eq(GOOGLE_PLAY_PROVIDER_ID), anyInt(), anyOrNull(), anyString()),
        ).thenReturn(wrapper)
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getFeed(anyString())).thenReturn(emptyList())
        `when`(dao.getGenres()).thenReturn(emptyList())
        `when`(api.trendingTVSeries(anyInt())).thenThrow(RuntimeException())
    }
}
