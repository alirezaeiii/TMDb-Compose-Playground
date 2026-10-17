package com.sample.tmdb.paging

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.domain.repository.BasePagingRepository
import io.mockk.mockk
import org.junit.Rule

open class BasePagingViewModelTest<T : TMDbItem> {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BasePagingRepository<Int, T>>()
}
