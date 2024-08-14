package com.sample.tmdb.data.repository

import android.content.Context
import app.cash.turbine.test
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.common.utils.Resource
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PersonResponse
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.domain.model.Person
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var api: PersonService

    private lateinit var repository: BaseRepository<Person>

    @Before
    fun setup() {
        repository = PersonRepository(api, context, Dispatchers.Main)
    }

    @Test
    fun `load person success`() {
        val personResponse = PersonResponse(
            "birth", "death", 1, "name",
            "biography", "placeOfBirth", "profilePath"
        )
        runTest {
            `when`(api.getPerson(anyString())).thenReturn(personResponse)
            repository.getResult(anyString()).test {
                assertEquals(Resource.Loading, awaitItem())
                awaitItem()
                val person = Resource.Success(personResponse.asDomainModel()).data
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
        `when` (context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            `when`(api.getPerson(anyString())).thenThrow(RuntimeException())
            repository.getResult(anyString()).test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}