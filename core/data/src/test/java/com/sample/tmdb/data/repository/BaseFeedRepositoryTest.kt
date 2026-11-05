package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseFeedRepository
import com.sample.tmdb.domain.utils.Async
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`

abstract class BaseFeedRepositoryTest<T : TMDbItem> : BaseRepositoryTest() {
    protected lateinit var repository: BaseFeedRepository<T>

    @Test
    fun `load feeds success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult().first(), `is`(Async.Loading()))
            val result = (repository.getResult().last() as Async.Success).data
            assertThat(result.size, `is`(10))
            assertThat(result[0].feeds.size, `is`(1))
            assertThat(result[1].feeds.size, `is`(1))
            assertThat(result[2].feeds.size, `is`(1))
            assertThat(result[3].feeds.size, `is`(1))
            assertThat(result[4].feeds.size, `is`(1))
            assertThat(result[5].feeds.size, `is`(1))
            assertThat(result[6].feeds.size, `is`(1))
            assertThat(result[7].feeds.size, `is`(1))
            assertThat(result[8].feeds.size, `is`(1))
            assertThat(result[9].feeds.size, `is`(1))
        }
    }

    @Test
    fun `load feeds cached`() {
        mockApiResponse()
        runTest {
            val firstResult = (repository.getResult().last() as Async.Success).data
            assertThat(firstResult.size, `is`(10))

            repository.getResult(forceRefresh = false).test {
                assertEquals(Async.Loading(), awaitItem())
                val cachedResult = (awaitItem() as Async.Success).data
                assertThat(cachedResult.size, `is`(10))
                awaitComplete()
            }
        }
    }

    @Test
    fun `load feeds failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockFailApiResponse()
        runTest {
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
