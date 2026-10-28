package com.sample.tmdb.preson

import app.cash.turbine.test
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.domain.model.Media
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseRepository
import com.sample.tmdb.domain.utils.Async
import com.sample.tmdb.navigation.TMDbNavKey
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PersonViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BaseRepository<Person, Int>>()

    private val person = mockk<Person>()

    private lateinit var viewModel: PersonViewModel

    @Test
    fun `load person`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load person success`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Success(person))
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState(person), viewModel.state.value)
    }

    @Test
    fun `load person failed`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Error("error"))
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState<Nothing>(error = "error"), viewModel.state.value)
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(PersonUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMediaClick with movie emits Navigate to MovieDetail`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        val media = Media(10, "url", MediaType.MOVIE, "Movie Title")
        viewModel.uiEvent.test {
            viewModel.onMediaClick(media)
            assertEquals(PersonUiEvent.Navigate(TMDbNavKey.MovieDetail(10)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMediaClick with tv show emits Navigate to TvShowDetail`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        val media = Media(20, "url", MediaType.TV_SHOW, "Tv Title")
        viewModel.uiEvent.test {
            viewModel.onMediaClick(media)
            assertEquals(PersonUiEvent.Navigate(TMDbNavKey.TvShowDetail(20)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        private const val PERSON_ID = 1
    }
}
