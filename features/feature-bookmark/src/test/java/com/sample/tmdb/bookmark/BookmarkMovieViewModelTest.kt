package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.navigation.TMDbNavKey
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkMovieViewModelTest : BaseBookmarkViewModelTest<Movie>() {

    override fun initViewModel() {
        viewModel = BookmarkMovieViewModel(repository, languageRepository)
    }

    @Test
    fun `onMovieClick emits Navigate to MovieDetail`() = runTest {
        val movie = Movie(15, "", null, null, null, "", 0.0, 0)

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(movie)
            assertEquals(BookmarkUiEvent.Navigate(TMDbNavKey.MovieDetail(15)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
