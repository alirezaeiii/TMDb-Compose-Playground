package com.sample.tmdb.data.repository.tvshow.detail

import com.sample.tmdb.data.repository.BaseBookmarkDetailsRepositoryTest
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkTVShowDetailsRepositoryTest: BaseBookmarkDetailsRepositoryTest<TVShow>() {

    @Mock
    private lateinit var dao: TVShowDao

    override fun initRepository() {
        repository = BookmarkTVShowDetailsRepositoryImpl(dao)
    }

    override fun mockApiResponse() = runTest {
        `when`(dao.isBookmarked(anyInt())).thenReturn(true)
    }
}