package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.MovieDetail
import com.sample.tmdb.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkMovieViewModelTest : BaseBookmarkViewModelTest<Movie>() {

    private lateinit var bookmarkMovieViewModel: BookmarkMovieViewModel

    override fun getViewModel(): BookmarkMovieViewModel {
        bookmarkMovieViewModel = BookmarkMovieViewModel(repository, languageRepository)
        return bookmarkMovieViewModel
    }

    @Test
    fun `onMovieClick emits Navigate to MovieDetail`() = runTest {
        val movie = mockk<Movie>(relaxed = true)
        every { movie.id } returns 15

        bookmarkMovieViewModel.uiEvent.test {
            bookmarkMovieViewModel.onTMDbItemClick(movie)
            assertEquals(BookmarkUiEvent.Navigate(MovieDetail(15)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
