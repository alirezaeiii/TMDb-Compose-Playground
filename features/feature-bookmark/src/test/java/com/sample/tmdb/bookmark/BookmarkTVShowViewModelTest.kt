package com.sample.tmdb.bookmark

import app.cash.turbine.test
import com.sample.tmdb.common.ui.TvShowDetail
import com.sample.tmdb.domain.model.TVShow
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookmarkTVShowViewModelTest : BaseBookmarkViewModelTest<TVShow>() {

    private lateinit var bookmarkTVShowViewModel: BookmarkTVShowViewModel

    override fun getViewModel(): BookmarkTVShowViewModel {
        bookmarkTVShowViewModel = BookmarkTVShowViewModel(repository, languageRepository)
        return bookmarkTVShowViewModel
    }

    @Test
    fun `onTVShowClick emits Navigate to TvShowDetail`() = runTest {
        val tvShow = mockk<TVShow>(relaxed = true)
        every { tvShow.id } returns 25

        bookmarkTVShowViewModel.uiEvent.test {
            bookmarkTVShowViewModel.onTMDbItemClick(tvShow)
            assertEquals(BookmarkUiEvent.Navigate(TvShowDetail(25)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
