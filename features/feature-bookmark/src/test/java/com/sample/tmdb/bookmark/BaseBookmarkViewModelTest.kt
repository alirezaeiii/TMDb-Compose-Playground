package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.common.ui.ViewState
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import com.sample.tmdb.domain.utils.Async
import com.sample.tmdb.feature.test.TMDbItemFixtures
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

abstract class BaseBookmarkViewModelTest<T : TMDbItem> : TMDbItemFixtures<T> {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BaseBookmarkRepository<T>>()

    protected val languageRepository = mockk<LanguageRepository>()

    protected lateinit var viewModel: BaseBookmarkViewModel<T>

    protected abstract fun initViewModel()

    @Before
    fun setup() {
        every {
            languageRepository.languageCode
        } returns MutableStateFlow("en")

        initViewModel()
    }

    private fun mockResult(result: Async<List<T>>) {
        every {
            repository.getResult(false, null)
        } returns flowOf(result)
    }

    @Test
    fun `load bookmarks`() = runTest {
        mockResult(Async.Loading())

        viewModel.refresh()

        assertEquals(
            ViewState<T>(isLoading = true),
            viewModel.state.value,
        )
    }

    @Test
    fun `load bookmarks success`() = runTest {
        mockResult(Async.Success(emptyList()))

        viewModel.refresh()

        assertEquals(
            ViewState(emptyList<T>()),
            viewModel.state.value,
        )
    }

    @Test
    fun `load bookmarks failed`() = runTest {
        mockResult(Async.Error("error"))

        viewModel.refresh()

        assertEquals(
            ViewState<T>(error = "error"),
            viewModel.state.value,
        )
    }

    @Test
    fun `load bookmarks warning emits showWarningUiEvent`() = runTest {
        mockResult(
            Async.Error(
                message = "warning message",
                isWarning = true,
            ),
        )

        viewModel.uiEvent.test {
            viewModel.refresh()

            assertEquals(
                UiEvent.ShowWarning("warning message"),
                awaitItem(),
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onItemClick emits Navigate to Detail`() = runTest {
        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(item)

            assertEquals(
                UiEvent.Navigate(navKey),
                awaitItem(),
            )

            cancelAndIgnoreRemainingEvents()
        }
    }
}
