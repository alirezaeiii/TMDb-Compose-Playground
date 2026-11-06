package com.sample.tmdb.preson

import app.cash.turbine.test
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.ui.UiEvent
import com.sample.tmdb.common.ui.ViewState
import com.sample.tmdb.domain.model.Media
import com.sample.tmdb.domain.model.MediaType
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.utils.Async
import com.sample.tmdb.navigation.TMDbNavKey
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PersonViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BaseCacheRepository<Person, Int, Int>>()
    private val languageRepository = mockk<com.sample.tmdb.domain.repository.LanguageRepository>()

    private val person = mockk<Person>()

    private lateinit var viewModel: PersonViewModel

    @Before
    fun setup() {
        every { languageRepository.languageCode } returns MutableStateFlow("en")
    }

    @Test
    fun `load person`() {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load person success`() {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Success(person))
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState(person), viewModel.state.value)
    }

    @Test
    fun `load person failed`() {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Error("error"))
        viewModel = PersonViewModel(repository, PERSON_ID)
        assertEquals(ViewState<Nothing>(error = "error"), viewModel.state.value)
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(UiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMediaClick with movie emits Navigate to MovieDetail`() = runTest {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        val media = Media(10, "url", MediaType.MOVIE, "Movie Title")
        viewModel.uiEvent.test {
            viewModel.onMediaClick(media)
            assertEquals(UiEvent.Navigate(TMDbNavKey.MovieDetail(10)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMediaClick with tv show emits Navigate to TvShowDetail`() = runTest {
        every { repository.getResult(any(), any(), any()) } returns flowOf(Async.Loading())
        viewModel = PersonViewModel(repository, PERSON_ID)

        val media = Media(20, "url", MediaType.TV_SHOW, "Tv Title")
        viewModel.uiEvent.test {
            viewModel.onMediaClick(media)
            assertEquals(UiEvent.Navigate(TMDbNavKey.TvShowDetail(20)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        private const val PERSON_ID = 1
    }
}
