package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`

abstract class BaseBookmarkRepositoryTest<T : TMDbItem> : BaseRepositoryTest() {
    protected lateinit var repository: BaseBookmarkRepository<T>

    @Test
    fun `load bookmark success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult().first(), `is`(Async.Loading()))
            val result = (repository.getResult().last() as Async.Success).data
            assertEquals(emptyList<T>(), result)
        }
    }

    @Test
    fun `load bookmark failed`() {
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
