package com.sample.tmdb.credit

import androidx.lifecycle.SavedStateHandle
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Resource
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

    private val repository = mockk<BaseRepository<Person>>()

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    private val person = mockk<Person>()

    private lateinit var viewModel: PersonViewModel

    @Test
    fun `load person`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Loading)
        viewModel = PersonViewModel(repository, savedStateHandle)
        assertEquals(Resource.Loading, viewModel.stateFlow.value)
    }

    @Test
    fun `load person success`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Success(person))
        viewModel = PersonViewModel(repository, savedStateHandle)
        assertEquals(Resource.Success(person), viewModel.stateFlow.value)
    }

    @Test
    fun `load person failed`() {
        every { repository.getResult(any()) } returns flowOf(Resource.Error(""))
        viewModel = PersonViewModel(repository, savedStateHandle)
        assertEquals(Resource.Error(""), viewModel.stateFlow.value)
    }
}