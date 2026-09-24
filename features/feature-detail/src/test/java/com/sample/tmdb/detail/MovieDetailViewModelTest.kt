package com.sample.tmdb.detail

import app.cash.turbine.test
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieDetails
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieDetailViewModelTest : BaseDetailViewModelTest<MovieDetails, Movie>() {
    override fun initViewModel() {
        super.viewModel = MovieDetailViewModel(bookmarkRepository, repository, TMDB_ITEM_ID)
    }

    override val tmdbItem: Movie
        get() =
            Movie(
                TMDB_ITEM_ID,
                "overview",
                null,
                null,
                null,
                "name",
                1.0,
                1,
            )

    @Test
    fun `onTMDbItemClick emits MovieNavigate`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(tmdbItem)
            assertEquals(DetailUiEvent.Navigate(MovieDetail(TMDB_ITEM_ID)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAllSimilarClick emits Navigate to SimilarMovies`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onAllSimilarClick(TMDB_ITEM_ID)
            assertEquals(DetailUiEvent.Navigate(SimilarMovies(TMDB_ITEM_ID)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
