package com.sample.tmdb.data.repository.tvshow

import app.cash.turbine.test
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseFeedRepositoryTest
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.domain.model.TVShow
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowFeedRepositoryTest : BaseFeedRepositoryTest<TVShow>() {
    @Mock
    private lateinit var api: TVShowService

    override fun initRepository() {
        repository = TVShowFeedRepository(context, Dispatchers.Main, api)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.trendingTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.popularTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.airingTodayTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.onTheAirTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.topRatedTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
        `when`(api.discoverTVSeries()).thenReturn(NetworkTMDbWrapper(emptyList()))
    }

    @Test
    fun `load feeds failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            `when`(api.trendingTVSeries()).thenThrow(RuntimeException())
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
