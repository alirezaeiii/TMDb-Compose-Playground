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

    @Test
    fun `onSearchClick emits Navigate to SearchMovies`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(FeedUiEvent.Navigate(SearchMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onTMDbItemClick emits Navigate to MovieDetail`() = runTest {
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
    fun `onMoreClick emits Navigate to MovieTrending`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onMoreClick(FeedNavigationEvent.More(ContentType.MOVIE, SortType.TRENDING))
            assertEquals(FeedUiEvent.Navigate(TrendingMovies), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
