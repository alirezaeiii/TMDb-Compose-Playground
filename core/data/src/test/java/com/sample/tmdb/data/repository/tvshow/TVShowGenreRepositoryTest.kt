package com.sample.tmdb.data.repository.tvshow

import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseRepositoryTest
import com.sample.tmdb.data.response.GenreDTO
import com.sample.tmdb.data.response.GenresDTO
import com.sample.tmdb.data.source.entity.TVShowGenreEntity
import com.sample.tmdb.data.source.local.TVShowFeedDao
import com.sample.tmdb.domain.utils.Async
import com.sample.tmdb.domain.utils.CacheTimestampDataStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer

@RunWith(MockitoJUnitRunner::class)
class TVShowGenreRepositoryTest : BaseRepositoryTest() {
    @Mock
    private lateinit var api: TVShowService

    @Mock
    private lateinit var dao: TVShowFeedDao

    private lateinit var repository: TVShowGenreRepository

    @Mock
    private lateinit var cacheTimestampStore: CacheTimestampDataStore

    override fun initRepository() {
        repository = TVShowGenreRepository(api, dao, context, Dispatchers.Main, cacheTimestampStore)
    }

    override fun mockApiResponse() = runTest {
        var dbGenres = emptyList<TVShowGenreEntity>()
        `when`(dao.getGenres()).thenAnswer { dbGenres }
        doAnswer { invocation ->
            dbGenres = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsertGenres(any())

        `when`(api.fetchGenres()).thenReturn(GenresDTO(listOf(GenreDTO(1, "Drama"))))
    }

    override fun mockFailApiResponse() = runTest {
        `when`(dao.getGenres()).thenReturn(emptyList())
        `when`(api.fetchGenres()).thenThrow(RuntimeException())
    }

    @Test
    fun `load genres success`() {
        mockApiResponse()
        runTest {
            val result = repository.getResult().last() as Async.Success
            val genres = result.data
            assertEquals(1, genres.size)
            assertEquals("Drama", genres[0].name)
        }
    }
}
