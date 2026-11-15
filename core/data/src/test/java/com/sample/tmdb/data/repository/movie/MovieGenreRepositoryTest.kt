package com.sample.tmdb.data.repository.movie

import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.repository.BaseRepositoryTest
import com.sample.tmdb.data.response.GenreDTO
import com.sample.tmdb.data.response.GenresDTO
import com.sample.tmdb.data.source.entity.MovieGenreEntity
import com.sample.tmdb.data.source.local.MovieFeedDao
import com.sample.tmdb.domain.utils.Async
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
class MovieGenreRepositoryTest : BaseRepositoryTest() {
    @Mock
    private lateinit var api: MovieService

    @Mock
    private lateinit var dao: MovieFeedDao

    private lateinit var repository: MovieGenreRepository

    override fun initRepository() {
        repository = MovieGenreRepository(api, dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() = runTest {
        var dbGenres = emptyList<MovieGenreEntity>()
        `when`(dao.getGenres()).thenAnswer { dbGenres }
        doAnswer { invocation ->
            dbGenres = invocation.getArgument(0)
            null
        }.`when`(dao).deleteAndInsertGenres(any())

        `when`(api.fetchGenres()).thenReturn(GenresDTO(listOf(GenreDTO(1, "Action"))))
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
            assertEquals("Action", genres[0].name)
        }
    }
}
