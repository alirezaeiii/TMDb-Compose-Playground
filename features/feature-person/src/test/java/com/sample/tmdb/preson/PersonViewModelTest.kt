package com.sample.tmdb.preson

import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.common.utils.ViewState
import com.sample.tmdb.domain.model.Person
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class PersonViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mockk<BaseRepository<Person, String>>()

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

    companion object {
        private const val PERSON_ID = 1
    }
}
