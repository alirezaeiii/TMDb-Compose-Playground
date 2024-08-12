package com.sample.tmdb.detail

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Resource
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

abstract class BaseDetailViewModelTest<T : TMDbItemDetails, R : TMDbItem> {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val bookmarkRepository = mockk<BookmarkItemDetailsRepository<R>>()

    protected val repository = mockk<BaseDetailRepository<T>>()

    protected val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    protected lateinit var viewModel: BaseDetailViewModel<T, R>

    protected abstract fun initViewModel()

    private val detailWrapper = mockk<DetailWrapper>()

    @Test
    fun `load details`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Loading)
        initViewModel()
        assertEquals(Resource.Loading, viewModel.stateFlow.value)
    }

    @Test
    fun `load details success`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Success(detailWrapper))
        initViewModel()
        assertEquals(Resource.Success(detailWrapper), viewModel.stateFlow.value)
    }

    @Test
    fun `load details failed`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Error(""))
        initViewModel()
        assertEquals(Resource.Error(""), viewModel.stateFlow.value)
    }
}