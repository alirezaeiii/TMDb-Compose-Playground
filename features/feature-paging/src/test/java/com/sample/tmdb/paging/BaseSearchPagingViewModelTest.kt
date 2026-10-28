package com.sample.tmdb.paging

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.feature.test.TMDbItemFixtures
import com.sample.tmdb.paging.search.BaseSearchTMDbPagingViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

abstract class BaseSearchPagingViewModelTest<T : TMDbItem> :
    BasePagingViewModelTest<T>(),
    TMDbItemFixtures<T> {

    protected val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    protected lateinit var viewModel: BaseSearchTMDbPagingViewModel<T>

    protected abstract fun initViewModel()

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
