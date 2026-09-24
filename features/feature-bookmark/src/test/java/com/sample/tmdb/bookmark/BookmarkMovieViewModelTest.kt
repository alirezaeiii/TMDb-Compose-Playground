package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkMovieViewModelTest : BaseBookmarkViewModelTest<Movie>() {

    override fun initViewModel() {
        viewModel = BookmarkMovieViewModel(repository, languageRepository)
    }

    @Test
    fun `onMovieClick emits Navigate to MovieDetail`() = runTest {
        val movie = mockk<Movie>(relaxed = true)
        every { movie.id } returns 15

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(movie)
            assertEquals(BookmarkUiEvent.Navigate(MovieDetail(15)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
