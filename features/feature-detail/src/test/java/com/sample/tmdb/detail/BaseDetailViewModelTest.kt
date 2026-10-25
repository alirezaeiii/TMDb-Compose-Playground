package com.sample.tmdb.detail

import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.repository.BookmarkDetailsRepository
import com.sample.tmdb.feature.test.TMDbItemFixtures
import com.sample.tmdb.navigation.model.Gender
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.TrendingPerson
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

abstract class BaseDetailViewModelTest<T : TMDbItemDetails, R : TMDbItem> : TMDbItemFixtures<R> {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val bookmarkRepository = mockk<BookmarkDetailsRepository<R>>()

    protected val repository = mockk<BaseDetailRepository<T>>()

    protected lateinit var viewModel: BaseDetailViewModel<T, R>

    protected abstract fun initViewModel()

    private val detailWrapper = mockk<DetailWrapper>()

    protected abstract val similarNavKey: TMDbNavKey

    @Test
    fun `load details`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()
        assertEquals(ViewState<Nothing>(isLoading = true), viewModel.state.value)
    }

    @Test
    fun `load details success`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Success(detailWrapper))
        initViewModel()
        assertEquals(ViewState(items = detailWrapper), viewModel.state.value)
    }

    @Test
    fun `load details failed`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Error("error"))
        initViewModel()
        assertEquals(ViewState<Nothing>(error = "error"), viewModel.state.value)
    }

    @Test
    fun `add bookmark`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coJustRun { bookmarkRepository.addBookmark(item) }
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns true
        initViewModel()
        viewModel.addBookmark(item)
        coVerify { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) }
        assertEquals(true, viewModel.isBookmarked.value)
    }

    @Test
    fun `remove bookmark`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coJustRun { bookmarkRepository.deleteBookmark(TMDB_ITEM_ID) }
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns false
        initViewModel()
        viewModel.removeBookmark(TMDB_ITEM_ID)
        coVerify { bookmarkRepository.deleteBookmark(TMDB_ITEM_ID) }
        assertEquals(false, viewModel.isBookmarked.value)
    }

    @Test
    fun `is bookmarked`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns true
        initViewModel()
        viewModel.isBookmarked(TMDB_ITEM_ID)
        assertEquals(true, viewModel.isBookmarked.value)
    }

    @Test
    fun `is not bookmarked`() {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        coEvery { bookmarkRepository.isBookmarked(TMDB_ITEM_ID) } returns false
        initViewModel()
        viewModel.isBookmarked(TMDB_ITEM_ID)
        assertEquals(false, viewModel.isBookmarked.value)
    }

    @Test
    fun `onPersonClick emits Navigate`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()
        val person = TrendingPerson(
            id = 5,
            name = "",
            subtitle = "",
            gender = Gender.MALE,
            profileUrl = null,
        )

        viewModel.uiEvent.test {
            viewModel.onPersonClick(person)
            assertEquals(DetailUiEvent.Navigate(TMDbNavKey.Person(person)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onNavigateUp emits NavigateUp`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onNavigateUp()
            assertEquals(DetailUiEvent.NavigateUp, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSeeAllCastClicked emits Navigate Cast event`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        val cast = listOf(
            TrendingPerson(
                id = 1,
                name = "",
                subtitle = "",
                gender = Gender.MALE,
                profileUrl = null,
            ),
        )
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSeeAllCastClicked(cast)
            val event = awaitItem()
            assertTrue(event is DetailUiEvent.Navigate)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onSeeAllCrewClicked emits Navigate Crew event`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        val crew = listOf(
            TrendingPerson(
                id = 1,
                name = "",
                subtitle = "",
                gender = Gender.MALE,
                profileUrl = null,
            ),
        )
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onSeeAllCrewClicked(crew)
            val event = awaitItem()
            assertTrue(event is DetailUiEvent.Navigate)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onImageSelected emits Navigate Image selected event`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        val images = listOf(
            TMDbImage(
                url = "",
                voteCount = 1,
            ),
        )
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onImageSelected(images, 0)
            val event = awaitItem()
            assertTrue(event is DetailUiEvent.Navigate)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onTMDbItemClick emits Navigate to ItemDetails`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf(Async.Loading())
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onTMDbItemClick(item)
            assertEquals(DetailUiEvent.Navigate(navKey), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAllSimilarClick emits Navigate to SimilarItems`() = runTest {
        every { repository.getResult(id = any()) } returns flowOf()
        initViewModel()

        viewModel.uiEvent.test {
            viewModel.onAllSimilarClick(TMDB_ITEM_ID)
            assertEquals(DetailUiEvent.Navigate(similarNavKey), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        const val TMDB_ITEM_ID = 10
    }
}
