package com.sample.tmdb.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.PeopleDTO
import com.sample.tmdb.data.response.TrendingPersonDTO
import com.sample.tmdb.data.response.asDomainModel
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonPagingSourceTest {

    @Mock
    private lateinit var api: PersonService

    @Mock
    private lateinit var context: Context

    private val item1 = TrendingPersonDTO(1, "", "", 1, null)

    private val item2 = TrendingPersonDTO(2, "", "", 1, null)

    private val people = PeopleDTO(
        results = listOf(item1, item2),
    )

    @Test
    fun pageKeyedShowPagingSource() = runTest {
        `when`(api.getTrendingPeople(1)).thenReturn(people)
        val dataSource = PersonPagingSource(api, context)

        assertEquals(
            expected = Page(
                data = people.asDomainModel().result,
                prevKey = null,
                nextKey = 2,
            ),
            actual = dataSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false,
                ),
            ),
        )
    }

    @Test
    fun pageKeyedShowPagingSourceWithQuery() = runTest {
        `when`(api.getTrendingPeople("", 1)).thenReturn(people)
        val dataSource = PersonPagingSource(api, context, "")

        assertEquals(
            expected = Page(
                data = people.asDomainModel().result,
                prevKey = null,
                nextKey = 2,
            ),
            actual = dataSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false,
                ),
            ),
        )
    }
}
