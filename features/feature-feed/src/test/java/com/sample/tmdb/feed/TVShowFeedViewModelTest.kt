package com.sample.tmdb.feed

import app.cash.turbine.test
import com.sample.tmdb.common.ui.SearchTvShows
import com.sample.tmdb.domain.model.TVShow
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TVShowFeedViewModelTest : BaseFeedViewModelTest<TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowFeedViewModel(repository, languageRepository)
    }

    @Test
    fun `onSearchClick emits Navigate to SearchTvShows`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(FeedUiEvent.Navigate(SearchTvShows), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
