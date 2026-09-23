package com.sample.tmdb.feed

import app.cash.turbine.test
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieFeedViewModelTest : BaseFeedViewModelTest<Movie>() {
    override fun initViewModel() {
        super.viewModel = MovieFeedViewModel(repository, languageRepository)
    }

    private val movieViewModel: MovieFeedViewModel
        get() = viewModel as MovieFeedViewModel

    @Test
    fun `onMovieClick emits Navigate to MovieDetail`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()
        val movie = mockk<Movie>(relaxed = true)
        every { movie.id } returns 10

        movieViewModel.uiEvent.test {
            movieViewModel.onMovieClick(movie)
            assertEquals(FeedUiEvent.Navigate(MovieDetail(10)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchClick emits Navigate to SearchMovies`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        movieViewModel.uiEvent.test {
            movieViewModel.onSearchClick()
            assertEquals(FeedUiEvent.Navigate(SearchMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMoreClick emits Navigate to TrendingMovies`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        movieViewModel.uiEvent.test {
            movieViewModel.onMoreClick(FeedNavigationEvent.More(ContentType.MOVIE, SortType.TRENDING))
            assertEquals(FeedUiEvent.Navigate(TrendingMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
