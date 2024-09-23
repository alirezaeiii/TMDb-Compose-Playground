package com.sample.tmdb.data.repository.tvshow.detail

import com.sample.tmdb.data.repository.BaseBookmarkRepositoryTest
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkTVShowRepositoryTest : BaseBookmarkRepositoryTest<TVShow>() {
    @Mock
    private lateinit var dao: TVShowDao

    override fun initRepository() {
        repository = BookmarkTVShowRepository(dao, context, Dispatchers.Main)
    }

    override fun mockApiResponse() =
        runTest {
            `when`(dao.getBookmarks()).thenReturn(emptyList())
        }
}
