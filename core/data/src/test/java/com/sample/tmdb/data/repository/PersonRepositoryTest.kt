package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PersonDTO
import com.sample.tmdb.data.source.entity.PersonEntity
import com.sample.tmdb.data.source.local.PersonDao
import com.sample.tmdb.domain.model.Person
import com.sample.tmdb.domain.repository.BaseCacheRepository
import com.sample.tmdb.domain.utils.Async
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PersonRepositoryTest : BaseRepositoryTest() {
    @Mock
    private lateinit var api: PersonService

    @Mock
    private lateinit var dao: PersonDao

    private lateinit var repository: BaseCacheRepository<Person, Int, Int>

    override fun initRepository() {
        repository = PersonRepository(api, dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        val dbEntities = mutableMapOf<Int, PersonEntity>()
        whenever(dao.getPerson(anyInt())).thenAnswer { invocation ->
            val id = invocation.getArgument<Int>(0)
            dbEntities[id]
        }
        doAnswer { invocation ->
            val entity = invocation.getArgument<PersonEntity>(0)
            dbEntities[entity.id] = entity
            null
        }.whenever(dao).insert(any())
        whenever(api.getPerson(anyInt())).thenAnswer { invocation ->
            val id = invocation.getArgument<Int>(0)
            val name = if (id == 1) "name" else "name $id"
            PersonDTO(
                "birth",
                "death",
                id,
                name,
                "biography",
                "placeOfBirth",
                "profilePath",
            )
        }
    }

    override fun mockFailApiResponse() = runTest {
        whenever(dao.getPerson(anyInt())).thenReturn(null)
        whenever(api.getPerson(anyInt())).thenThrow(RuntimeException())
    }

    @Test
    fun `load person success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult(queryParam = 1, fetchParam = 1).first(), `is`(Async.Loading()))
            val person = (repository.getResult(queryParam = 1, fetchParam = 1).last() as Async.Success).data
            assertEquals("birth", person.birthDay)
            assertEquals("death", person.deathDay)
            assertEquals(1, person.id)
            assertEquals("name", person.name)
            assertEquals("biography", person.biography)
            assertEquals("placeOfBirth", person.placeOfBirth)
            assertEquals("http://image.tmdb.org/t/p/w342profilePath", person.profilePath)
        }
    }

    @Test
    fun `load person cached`() {
        mockApiResponse()
        runTest {
            val firstResult = (repository.getResult(queryParam = 1, fetchParam = 1).last() as Async.Success).data
            assertEquals(1, firstResult.id)

            repository.getResult(queryParam = 1, fetchParam = 1, forceRefresh = false).test {
                assertEquals(Async.Loading(), awaitItem())
                val cachedResult = (awaitItem() as Async.Success).data
                assertEquals(1, cachedResult.id)
                awaitComplete()
            }
        }
    }

    @Test
    fun `load person failed`() {
        val errorMsg = "error message"
        whenever(context.getString(anyInt())).thenReturn(errorMsg)
        mockFailApiResponse()
        runTest {
            repository.getResult(queryParam = 1, fetchParam = 1).test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
