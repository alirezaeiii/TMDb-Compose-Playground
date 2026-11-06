package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PeopleDTO
import com.sample.tmdb.data.response.TrendingPersonDTO
import com.sample.tmdb.data.source.entity.PersonFeedEntity
import com.sample.tmdb.data.source.local.PersonFeedDao
import com.sample.tmdb.domain.model.Gender
import com.sample.tmdb.domain.model.People
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
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PeopleRepositoryTest : BaseRepositoryTest() {

    @Mock
    private lateinit var api: PersonService

    @Mock
    private lateinit var dao: PersonFeedDao

    private lateinit var repository: BaseCacheRepository<People, Unit, Unit>

    private val item1 = TrendingPersonDTO(1, "name", "subtitle", 1, null)

    private val peopleDTO = PeopleDTO(
        results = listOf(item1),
    )

    override fun initRepository() {
        repository = PeopleRepository(api, dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        var dbEntities = emptyList<PersonFeedEntity>()
        `when`(dao.getFeed()).thenAnswer { dbEntities }
        doAnswer { invocation ->
            dbEntities = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsert(anyList())

        `when`(api.getTrendingPeople()).thenReturn(peopleDTO)
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getFeed()).thenReturn(emptyList())
        `when`(api.getTrendingPeople()).thenThrow(RuntimeException())
    }

    @Test
    fun `load people success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult().first(), `is`(Async.Loading()))
            val result = (repository.getResult().last() as Async.Success).data
            assertEquals(1, result.result.size)
            assertEquals(1, result.result[0].id)
            assertEquals("name", result.result[0].name)
            assertEquals("subtitle", result.result[0].subtitle)
            assertEquals(Gender.FEMALE, result.result[0].gender)
        }
    }

    @Test
    fun `load people cached`() {
        mockApiResponse()
        runTest {
            val firstResult = (repository.getResult().last() as Async.Success).data
            assertEquals(1, firstResult.result.size)

            repository.getResult(forceRefresh = false).test {
                assertEquals(Async.Loading(), awaitItem())
                val cachedResult = (awaitItem() as Async.Success).data
                assertEquals(1, cachedResult.result.size)
                awaitComplete()
            }
        }
    }

    @Test
    fun `load people failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockFailApiResponse()
        runTest {
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
