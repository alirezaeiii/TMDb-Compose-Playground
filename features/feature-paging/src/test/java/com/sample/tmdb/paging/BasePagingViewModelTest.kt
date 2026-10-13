package com.sample.tmdb.paging

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

abstract class BasePagingViewModelTest<T : TMDbItem, VM : BasePagingViewModel<T>> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BasePagingRepository<T>>()

    protected val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    protected lateinit var viewModel: VM

    protected abstract fun initViewModel()

    protected abstract val item: TMDbItem

    protected abstract val navKey: TMDbNavKey

    @Test
    fun `onItemClick emits Navigate to Detail`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onItemClick(item)
            assertEquals(PagingUiEvent.Navigate(navKey), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PagingUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
