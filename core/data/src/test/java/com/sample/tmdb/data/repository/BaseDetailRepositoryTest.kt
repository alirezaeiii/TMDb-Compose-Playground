package com.sample.tmdb.data.repository

import app.cash.turbine.test
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.utils.Resource
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.SpokenLanguage
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`

abstract class BaseDetailRepositoryTest<T : TMDbItemDetails>: BaseRepositoryTest() {

    protected lateinit var repository: BaseDetailRepository<T>

    @Test
    fun `load details success`() {
        mockApiResponse()
        runTest {
            assertThat(repository.getResult(anyInt()).first(), `is`(Resource.Loading))
            val result = (repository.getResult(anyInt()).last() as Resource.Success).data
            assertEquals(ID, result.details.id)
            assertEquals(emptyList<Genre>(), result.details.genres)
            assertEquals(STATUS, result.details.status)
            assertEquals(TITLE, result.details.title)
            assertEquals(TAG_LINE, result.details.tagline)
            assertEquals("http://image.tmdb.org/t/p/w780backdropPath", result.details.backdropPath)
            assertEquals("http://image.tmdb.org/t/p/w342posterPath", result.details.posterPath)
            assertEquals(HOMEPAGE, result.details.homepage)
            assertEquals(ORIGINAL_LANGUAGE, result.details.originalLanguage)
            assertEquals(ORIGINAL_TITLE, result.details.originalTitle)
            assertEquals(OVERVIEW, result.details.overview)
            assertEquals(POPULARITY, result.details.popularity)
            assertEquals(RELEASE_DATE, result.details.releaseDate)
            assertEquals(emptyList<SpokenLanguage>(), result.details.spokenLanguages)
            assertEquals(VOTE_AVERAGE, result.details.voteAverage)
            assertEquals(VOTE_COUNT, result.details.voteCount)
            assertEquals(emptyList<Cast>(), result.cast)
            assertEquals(emptyList<Crew>(), result.crew)
            assertEquals(emptyList<TMDbImage>(), result.images)
            assertEquals(emptyList<TMDbItem>(), result.similarItems)
        }
    }

    @Test
    fun `load feeds failed`() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        runTest {
            repository.getResult(anyInt()).test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Error(errorMsg), awaitItem())
                awaitComplete()
            }
        }
    }

    companion object {
        const val ID = 1
        const val STATUS = "status"
        const val TITLE = "title"
        const val TAG_LINE = "tagline"
        const val BACKDROP_PATH = "backdropPath"
        const val POSTER_PATH = "posterPath"
        const val HOMEPAGE = "homepage"
        const val ORIGINAL_LANGUAGE = "originalLanguage"
        const val ORIGINAL_TITLE = "originalTitle"
        const val OVERVIEW = "overview"
        const val POPULARITY = 5.0
        const val RELEASE_DATE = "releaseDate"
        const val VOTE_AVERAGE = 1.0
        const val VOTE_COUNT = 10
    }
}