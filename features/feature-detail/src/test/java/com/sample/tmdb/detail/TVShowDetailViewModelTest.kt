package com.sample.tmdb.detail

import app.cash.turbine.test
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TVShowDetailViewModelTest : BaseDetailViewModelTest<TVShowDetails, TVShow>() {
    override fun initViewModel() {
        super.viewModel = TVShowDetailViewModel(bookmarkRepository, repository, TMDB_ITEM_ID)
    }

    override val tmdbItem: TVShow
        get() =
            TVShow(
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
    fun `onAllSimilarClick emits Navigate to SimilarTvShows`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onAllSimilarClick(TMDB_ITEM_ID)
            assertEquals(DetailUiEvent.Navigate(SimilarTvShows(TMDB_ITEM_ID)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
