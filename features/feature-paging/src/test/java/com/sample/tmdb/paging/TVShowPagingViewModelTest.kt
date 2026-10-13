package com.sample.tmdb.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.navigation.SortType
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.paging.main.TvShowPagingViewModel
import io.mockk.every
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TVShowPagingViewModelTest : BasePagingViewModelTest<TVShow, TvShowPagingViewModel>() {

    override fun initViewModel() {
        super.viewModel = TvShowPagingViewModel(repository, type = SortType.TRENDING)
    }

    override val item: TMDbItem
        get() = TVShow(200, "", null, null, null, "", 0.0, 0)

    override val navKey: TMDbNavKey
        get() = TMDbNavKey.TvShowDetail(200)

    @Test
    fun `onSearchClick tvShow emits Navigate to SearchTvShows`() = runTest {
        every { repository.fetchResultStream(any(), any(), any()) } returns flowOf(PagingData.empty())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.SearchTvShows), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
