package com.sample.tmdb.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.feature.test.TMDbItemFixtures
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.BaseMainPagingViewModel
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

abstract class BaseMainPagingViewModelTest<T : TMDbItem> :
    BasePagingViewModelTest<T>(),
    TMDbItemFixtures {
    protected lateinit var viewModel: BaseMainPagingViewModel<T>

    protected abstract fun initViewModel(sortType: SortType)

    protected abstract val searchNavKey: TMDbNavKey

    @Test
    fun `onItemClick emits Navigate to Detail`() = runTest {
        SortType.entries.forEach { sortType ->
            every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
            initViewModel(sortType)

            viewModel.uiEvent.test {
                viewModel.onItemClick(item)
                assertEquals(PagingUiEvent.Navigate(navKey), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        SortType.entries.forEach { sortType ->
            every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
            initViewModel(sortType)

            viewModel.uiEvent.test {
                viewModel.onNavigateUp()
                assertEquals(PagingUiEvent.NavigateUp, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `onSearchClick emits Navigate to SearchItems`() = runTest {
        SortType.entries.forEach { sortType ->
            every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
            initViewModel(sortType)

            viewModel.uiEvent.test {
                viewModel.onSearchClick()
                assertEquals(PagingUiEvent.Navigate(searchNavKey), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
