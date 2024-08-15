package com.sample.tmdb.data.repository.tvshow.detail

import com.sample.tmdb.data.repository.BaseBookmarkDetailsRepositoryTest
import com.sample.tmdb.data.repository.movie.detail.BookmarkMovieDetailsRepositoryImpl
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkMovieDetailsRepositoryTest: BaseBookmarkDetailsRepositoryTest<Movie>() {

    @Mock
    private lateinit var dao: MovieDao

    override fun initRepository() {
        repository = BookmarkMovieDetailsRepositoryImpl(dao)
    }

    override fun mockApiResponse() = runTest {
        `when`(dao.isBookmarked(anyInt())).thenReturn(true)
    }
}