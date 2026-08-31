package com.sample.tmdb.detail

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

abstract class BaseDetailViewModelTest<T : TMDbItemDetails, R : TMDbItem> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val bookmarkRepository = mockk<BookmarkDetailsRepository<R>>()

    protected val repository = mockk<BaseDetailRepository<T>>()

    protected lateinit var viewModel: BaseDetailViewModel<T, R>

    protected abstract fun initViewModel()

    private val detailWrapper = mockk<DetailWrapper>()

    protected abstract val tmdbItem: R

    @Test
    fun `load details`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load details success`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Success(detailWrapper))
        initViewModel()
        assertEquals(ViewState(items = detailWrapper), viewModel.state.value)
    }

    @Test
    fun `load details failed`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Error("error"))
        initViewModel()
        assertEquals(ViewState<Nothing>(error = "error"), viewModel.state.value)
    }

    @Test
    fun `add bookmark`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coJustRun { bookmarkRepository.addBookmark(tmdbItem) }
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns true
        initViewModel()
        viewModel.addBookmark(tmdbItem)
        coVerify { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) }
        assertEquals(true, viewModel.isBookmarked.value)
    }

    @Test
    fun `remove bookmark`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coJustRun { bookmarkRepository.deleteBookmark(TMDB_ITEM_ID) }
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns false
        initViewModel()
        viewModel.removeBookmark(TMDB_ITEM_ID)
        coVerify { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) }
        assertEquals(false, viewModel.isBookmarked.value)
    }

    @Test
    fun `is bookmarked`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns true
        initViewModel()
        viewModel.isBookmarked(TMDB_ITEM_ID)
        assertEquals(true, viewModel.isBookmarked.value)
    }

    @Test
    fun `is not bookmarked`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns false
        initViewModel()
        viewModel.isBookmarked(TMDB_ITEM_ID)
        assertEquals(false, viewModel.isBookmarked.value)
    }

    companion object {
        const val TMDB_ITEM_ID = 1
    }
}
