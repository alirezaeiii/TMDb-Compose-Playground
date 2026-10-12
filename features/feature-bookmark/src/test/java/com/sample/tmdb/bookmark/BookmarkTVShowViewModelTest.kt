package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.TMDbNavKey
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkTVShowViewModelTest : BaseBookmarkViewModelTest<TVShow>() {

    override fun initViewModel() {
        viewModel = BookmarkTVShowViewModel(repository, languageRepository)
    }

    @Test
    fun `onTVShowClick emits Navigate to TvShowDetail`() = runTest {
        val tvShow = TVShow(25, "", null, null, null, "", 0.0, 0)

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(tvShow)
            assertEquals(BookmarkUiEvent.Navigate(TMDbNavKey.TvShowDetail(25)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
