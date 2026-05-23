package com.sample.tmdb.data.repository.tvshow.detail

import app.cash.turbine.test
import com.sample.tmdb.common.utils.Async
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.repository.BaseBookmarkRepositoryTest
import com.sample.tmdb.data.source.local.TVShowDao
import com.sample.tmdb.domain.model.TVShow
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkTVShowRepositoryTest : BaseBookmarkRepositoryTest<TVShow>() {
    @Mock
    private lateinit var dao: TVShowDao

    @Mock
    private lateinit var api: TVShowService

    override fun initRepository() {
        repository = BookmarkTVShowRepository(
            dao,
            api,
            context,
            Dispatchers.Main,
        )
    }

    override fun mockApiResponse() = runTest {
        `when`(dao.getBookmarks()).thenReturn(emptyList())
    }

    @Test
    fun `load bookmark failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            `when`(dao.getBookmarks()).thenThrow(RuntimeException())
            repository.getResult().test {
                assertEquals(Async.Loading(), awaitItem())
                assertEquals(Async.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }
}
