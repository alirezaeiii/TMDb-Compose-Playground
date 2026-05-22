package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PersonDTO
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.domain.model.Person
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonRepositoryTest : BaseRepositoryTest() {
    @Mock
    private lateinit var api: PersonService

    private lateinit var repository: BaseRepository<Person, String>

    private val personDto =
        PersonDTO(
            "birth",
            "death",
            1,
            "name",
            "biography",
            "placeOfBirth",
            "profilePath",
        )

    override fun initRepository() {
        repository = PersonRepository(api, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.getPerson(anyString())).thenReturn(personDto)
    }

    @Test
    fun `load person success`() {
        mockApiResponse()
        runTest {
            repository.getResult(id = anyString()).test {
                assertEquals(Async.Loading(), awaitItem())
                awaitItem()
                val person = Async.Success(personDto.asDomainModel()).data
                assertEquals("birth", person.birthDay)
                assertEquals("death", person.deathDay)
                assertEquals(1, person.id)
                assertEquals("name", person.name)
                assertEquals("biography", person.biography)
                assertEquals("placeOfBirth", person.placeOfBirth)
                assertEquals("http://image.tmdb.org/t/p/w342profilePath", person.profilePath)
                awaitComplete()
            }
        }
    }

    @Test
    fun `load person failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            `when`(api.getPerson(anyString())).thenThrow(RuntimeException())
            repository.getResult(id = anyString()).test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
