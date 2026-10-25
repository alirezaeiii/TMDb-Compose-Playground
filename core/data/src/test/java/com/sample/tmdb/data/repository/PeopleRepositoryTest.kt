package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.People
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PeopleDTO
import com.sample.tmdb.data.response.TrendingPersonDTO
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.navigation.Gender
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PeopleRepositoryTest : BaseRepositoryTest() {

    @Mock
    private lateinit var api: PersonService

    private lateinit var repository: BaseRepository<People, Nothing>

    private val item1 = TrendingPersonDTO(1, "name", "subtitle", 1, null)

    private val peopleDTO = PeopleDTO(
        results = listOf(item1),
    )

    override fun initRepository() {
        repository = PeopleRepository(api, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        `when`(api.getTrendingPeople()).thenReturn(peopleDTO)
    }

    @Test
    fun `load person success`() {
        mockApiResponse()
        runTest {
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                awaitItem()
                val person = Async.Success(peopleDTO.asDomainModel()).data
                val result = person.result
                assertEquals(1, result.size)
                assertEquals(1, result[0].id)
                assertEquals("name", result[0].name)
                assertEquals("subtitle", result[0].subtitle)
                assertEquals(Gender.FEMALE, result[0].gender)
                awaitComplete()
            }
        }
    }
}
