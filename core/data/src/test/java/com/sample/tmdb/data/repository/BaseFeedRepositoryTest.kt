package com.sample.tmdb.data.repository

import android.content.Context
import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Resource
import com.sample.tmdb.domain.repository.BaseFeedRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`

abstract class BaseFeedRepositoryTest<T: TMDbItem> {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    protected lateinit var context: Context

    protected lateinit var repository: BaseFeedRepository<T>

    protected abstract fun initRepository()

    protected abstract fun mockApiResponse()

    @Before
    fun setup() {
        initRepository()
    }

    @Test
    fun `load feeds`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult(null).first(), `is`(Resource.Loading))
            val result = (repository.getResult(null).last() as Resource.Success).data
            assertThat(result[0].feeds, `is`(emptyList()))
            assertThat(result[1].feeds, `is`(emptyList()))
            assertThat(result[2].feeds, `is`(emptyList()))
            assertThat(result[3].feeds, `is`(emptyList()))
            assertThat(result[4].feeds, `is`(emptyList()))
            assertThat(result[5].feeds, `is`(emptyList()))
        }
    }

    @Test
    fun `load feeds failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            repository.getResult(null).test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}