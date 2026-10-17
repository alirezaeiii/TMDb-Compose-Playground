package com.sample.tmdb.person.paging

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import app.cash.turbine.test
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.common.utils.Gender
import com.sample.tmdb.domain.repository.BasePagingRepository
import com.sample.tmdb.navigation.TMDbNavKey
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PagingSearchPersonViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BasePagingRepository<TrendingPerson>>()
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private val person = TrendingPerson(10, "name", "subtitle", Gender.MALE, null)

    @Test
    fun `onItemClick emits Navigate to Person`() = runTest {
        every { repository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = PagingSearchPersonViewModel(repository, savedStateHandle)

        viewModel.uiEvent.test {
            viewModel.onItemClick(person)
            assertEquals(PagingUiEvent.Navigate(TMDbNavKey.Person(person.id)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.fetchResultStream(any()) } returns flowOf(PagingData.empty())
        val viewModel = PagingSearchPersonViewModel(repository, savedStateHandle)

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PagingUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
