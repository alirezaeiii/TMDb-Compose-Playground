package com.sample.tmdb.feed

import app.cash.turbine.test
import com.sample.tmdb.common.ui.SearchMovies
import com.sample.tmdb.domain.model.Movie
import io.mockk.every
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
}
