package com.sample.tmdb.feed

import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.repository.BaseFeedRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

abstract class BaseFeedViewModelTest<T : TMDbItem> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val repository = mockk<BaseFeedRepository<T>>()

    protected val languageRepository = mockk<LanguageRepository>()

    protected lateinit var viewModel: BaseFeedViewModel<T>

    protected abstract fun initViewModel()

    @Before
    fun setup() {
        every { languageRepository.languageCode } returns MutableStateFlow("en")
    }

    @Test
    fun `load feeds`() {
        every { repository.getResult() } returns flowOf(Async.Loading())
        initViewModel()
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load feeds success`() {
        every { repository.getResult() } returns flowOf(Async.Success(emptyList()))
        initViewModel()
        assertEquals(ViewState(emptyList<FeedWrapper>()), viewModel.state.value)
    }

    @Test
    fun `load feeds failed`() {
        every { repository.getResult() } returns flowOf(Async.Error("error"))
        initViewModel()
        assertEquals(ViewState<Nothing>(error = "error"), viewModel.state.value)
    }

    @Test
    fun `load feeds warning emits showWarningUiEvent`() = runTest {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Success(emptyList()))
        initViewModel()
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(
            Async.Error(
                "warning message",
                isWarning = true,
            ),
        )
        viewModel.uiEvent.test {
            viewModel.refresh()
            assertEquals(FeedUiEvent.ShowWarning("warning message"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onTMDbItemClick emits Navigate to Detail`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()
        val movie = mockk<Movie>(relaxed = true)
        every { movie.id } returns 10

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(movie)
            assertEquals(FeedUiEvent.Navigate(MovieDetail(10)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMoreClick emits Navigate to Trending`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onMoreClick(FeedNavigationEvent.More(ContentType.MOVIE, SortType.TRENDING))
            assertEquals(FeedUiEvent.Navigate(TrendingMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
