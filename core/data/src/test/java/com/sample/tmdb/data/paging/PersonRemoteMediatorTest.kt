package com.sample.tmdb.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PeopleDTO
import com.sample.tmdb.data.response.TrendingPersonDTO
import com.sample.tmdb.data.source.entity.PersonPagingEntity
import com.sample.tmdb.data.source.entity.PersonSearchPagingEntity
import com.sample.tmdb.data.source.local.PersonPagingDao
import com.sample.tmdb.data.source.local.PersonRemoteKeyDao
import com.sample.tmdb.data.source.local.PersonSearchPagingDao
import com.sample.tmdb.data.source.local.PersonSearchRemoteKeyDao
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class PersonRemoteMediatorTest : BaseRemoteMediatorTest() {

    private val api: PersonService = mockk()
    private val personPagingDao: PersonPagingDao = mockk(relaxed = true)
    private val personRemoteKeyDao: PersonRemoteKeyDao = mockk(relaxed = true)
    private val personSearchPagingDao: PersonSearchPagingDao = mockk(relaxed = true)
    private val personSearchRemoteKeyDao: PersonSearchRemoteKeyDao = mockk(relaxed = true)

    private val item1 = TrendingPersonDTO(1, "name1", "profile1", 1, null)
    private val item2 = TrendingPersonDTO(2, "name2", "profile2", 1, null)

    private val people = PeopleDTO(
        results = listOf(item1, item2),
    )

    @Before
    fun setup() {
        every { database.personPagingDao } returns personPagingDao
        every { database.personRemoteKeyDao } returns personRemoteKeyDao
        every { database.personSearchPagingDao } returns personSearchPagingDao
        every { database.personSearchRemoteKeyDao } returns personSearchRemoteKeyDao
    }

    @Test
    fun refreshPersonRemoteMediatorSuccess() = runTest {
        coEvery { api.getTrendingPeople(1) } returns people
        val mediator = PersonRemoteMediator(database, api, context)
        val pagingState = PagingState<Int, PersonPagingEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(20),
            leadingPlaceholderCount = 0,
        )
        val result = mediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse(result.endOfPaginationReached)
    }

    @Test
    fun refreshPersonSearchRemoteMediatorSuccess() = runTest {
        val query = "query"
        coEvery { api.getTrendingPeople(query, 1) } returns people
        val mediator = PersonSearchRemoteMediator(database, api, query, context)
        val pagingState = PagingState<Int, PersonSearchPagingEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(20),
            leadingPlaceholderCount = 0,
        )
        val result = mediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse(result.endOfPaginationReached)
    }
}
