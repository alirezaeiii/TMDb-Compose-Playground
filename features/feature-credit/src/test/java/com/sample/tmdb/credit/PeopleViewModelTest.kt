package com.sample.tmdb.credit

import app.cash.turbine.test
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.People
import com.sample.tmdb.common.repository.LanguageRepository
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.Gender
import com.sample.tmdb.navigation.model.TrendingPerson
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PeopleViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BaseRepository<People, Nothing>>()
    private val languageRepository = mockk<LanguageRepository>()

    private val person = TrendingPerson(
        id = 1,
        name = "John Doe",
        subtitle = "Actor",
        gender = Gender.MALE,
        profileUrl = "https://image.tmdb.org/t/p/w500/test.jpg",
    )
    private val people = People(result = listOf(person))

    private lateinit var viewModel: PeopleViewModel

    @Before
    fun setup() {
        every { languageRepository.languageCode } returns MutableStateFlow("en")
    }

    @Test
    fun `load people`() {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Loading())
        viewModel = PeopleViewModel(repository, languageRepository)
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load people success`() {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Success(people))
        viewModel = PeopleViewModel(repository, languageRepository)
        assertEquals(ViewState(people), viewModel.state.value)
    }

    @Test
    fun `load people failed`() {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Error("error message"))
        viewModel = PeopleViewModel(repository, languageRepository)
        assertEquals(ViewState<Nothing>(error = "error message"), viewModel.state.value)
    }

    @Test
    fun `load people warning emits ShowWarning`() = runTest {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Success(people))
        viewModel = PeopleViewModel(repository, languageRepository)

        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(
            Async.Error("warning message", isWarning = true),
        )

        viewModel.uiEvent.test {
            viewModel.refresh()
            assertEquals(PeopleUIEvent.ShowWarning("warning message"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onPersonClick emits Navigate to Person`() = runTest {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Loading())
        viewModel = PeopleViewModel(repository, languageRepository)

        viewModel.uiEvent.test {
            viewModel.onPersonClick(person)
            assertEquals(PeopleUIEvent.Navigate(TMDbNavKey.Person(person)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onMoreClick emits Navigate to PersonPaging`() = runTest {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Loading())
        viewModel = PeopleViewModel(repository, languageRepository)

        viewModel.uiEvent.test {
            viewModel.onMoreClick()
            assertEquals(PeopleUIEvent.Navigate(TMDbNavKey.PersonPaging), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSearchClick emits Navigate to SearchPerson`() = runTest {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Loading())
        viewModel = PeopleViewModel(repository, languageRepository)

        viewModel.uiEvent.test {
            viewModel.onSearchClick()
            assertEquals(PeopleUIEvent.Navigate(TMDbNavKey.SearchPerson), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `refreshOnLanguageChange reloads data when language changes`() {
        every { repository.getResult(isRefreshing = false, id = null) } returns flowOf(Async.Success(people))
        viewModel = PeopleViewModel(repository, languageRepository)

        every { repository.getResult(isRefreshing = true, id = null) } returns flowOf(Async.Success(people))
        viewModel.refreshOnLanguageChange("es")

        assertEquals(ViewState(people), viewModel.state.value)
    }
}
