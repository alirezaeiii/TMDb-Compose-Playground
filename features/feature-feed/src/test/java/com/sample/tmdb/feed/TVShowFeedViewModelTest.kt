package com.sample.tmdb.feed

import app.cash.turbine.test
import com.sample.tmdb.common.ui.SearchTvShows
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import io.mockk.every
import io.mockk.mockk
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

    @Test
    fun `onMoreClick emits Navigate to TVShowTrending`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onMoreClick(FeedNavigationEvent.More(ContentType.TV_SHOW, SortType.TRENDING))
            assertEquals(FeedUiEvent.Navigate(TrendingTvShows), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onTMDbItemClick emits Navigate to TVShowDetail`() = runTest {
        every { repository.getResult() } returns flowOf()
        initViewModel()
        val tvShow = mockk<TVShow>(relaxed = true)
        every { tvShow.id } returns 10

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(tvShow)
            assertEquals(FeedUiEvent.Navigate(TvShowDetail(10)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
