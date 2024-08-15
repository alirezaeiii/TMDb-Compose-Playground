package com.sample.tmdb.data.repository

import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.domain.repository.BookmarkItemDetailsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

abstract class BaseBookmarkDetailsRepositoryTest<T : TMDbItem> : BaseRepositoryTest() {

    protected lateinit var repository: BookmarkItemDetailsRepository<T>

    @Test
    fun `is Bookmarked`() {
        mockApiResponse()
        runTest {
            assertEquals(true, repository.isBookmarked(anyInt()))
        }
    }
}