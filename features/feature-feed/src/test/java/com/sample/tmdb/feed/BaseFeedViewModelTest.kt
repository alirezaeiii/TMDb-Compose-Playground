package com.sample.tmdb.feed

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Resource
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.repository.BaseFeedRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

abstract class BaseFeedViewModelTest<T : TMDbItem> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BaseFeedRepository<T>>()

    protected lateinit var viewModel: BaseFeedViewModel<T>

    protected abstract fun initViewModel()

    @Test
    fun `load feeds`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Loading)
        initViewModel()
        assertEquals(Resource.Loading, viewModel.stateFlow.value)
    }

    @Test
    fun `load feeds success`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Success(emptyList()))
        initViewModel()
        assertEquals(Resource.Success(emptyList<FeedWrapper>()), viewModel.stateFlow.value)
    }

    @Test
    fun `load feeds failed`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Error(""))
        initViewModel()
        assertEquals(Resource.Error(""), viewModel.stateFlow.value)
    }
}
