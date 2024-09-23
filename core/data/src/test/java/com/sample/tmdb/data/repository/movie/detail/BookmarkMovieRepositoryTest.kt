package com.sample.tmdb.data.repository.movie.detail

import com.sample.tmdb.data.repository.BaseBookmarkRepositoryTest
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkMovieRepositoryTest : BaseBookmarkRepositoryTest<Movie>() {
    @Mock
    private lateinit var dao: MovieDao

    override fun initRepository() {
        repository = BookmarkMovieRepository(dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() =
        runTest {
            `when`(dao.getBookmarks()).thenReturn(emptyList())
        }
}
