package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.utils.Resource
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
    protected lateinit var repository: BaseRepository<List<T>>

    @Test
    fun `load bookmark success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult(null).first(), `is`(Resource.Loading))
            val result = (repository.getResult(null).last() as Resource.Success).data
            assertEquals(emptyList<T>(), result)
        }
    }

    @Test
    fun `load bookmark failed`() = runTest {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        repository.getResult(null).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Error(errorMsg), awaitItem())
            awaitComplete()
        }
    }
}
