package com.sample.tmdb.person.paging

import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.Gender
import com.sample.tmdb.navigation.model.TrendingPerson
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PersonPagingViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BasePagingRepository<TrendingPerson>>()
    private val person = TrendingPerson(10, "name", "subtitle", Gender.MALE, null)

    @Test
    fun `onItemClick emits Navigate to Person`() = runTest {
        every { repository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = PersonPagingViewModel(repository)

        viewModel.uiEvent.test {
            viewModel.onItemClick(person)
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.Person(person)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = PersonPagingViewModel(repository)

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PagingUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchClick emits Navigate to SearchPerson`() = runTest {
        every { repository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = PersonPagingViewModel(repository)

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.SearchPerson), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
