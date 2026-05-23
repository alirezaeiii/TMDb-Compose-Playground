package com.sample.tmdb.data.repository

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.domain.repository.BaseFeedRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

abstract class BaseFeedRepositoryTest<T : TMDbItem> : BaseRepositoryTest() {
    protected lateinit var repository: BaseFeedRepository<T>

    @Test
    fun `load feeds success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult().first(), `is`(Async.Loading()))
            val result = (repository.getResult().last() as Async.Success).data
            assertThat(result[0].feeds, `is`(emptyList()))
            assertThat(result[1].feeds, `is`(emptyList()))
            assertThat(result[2].feeds, `is`(emptyList()))
            assertThat(result[3].feeds, `is`(emptyList()))
            assertThat(result[4].feeds, `is`(emptyList()))
            assertThat(result[5].feeds, `is`(emptyList()))
        }
    }
}
