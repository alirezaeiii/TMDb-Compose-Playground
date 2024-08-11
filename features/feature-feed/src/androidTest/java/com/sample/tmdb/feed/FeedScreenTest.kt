package com.sample.tmdb.feed

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.domain.R
import com.sample.tmdb.domain.model.Movie
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class FeedScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun trendingItemTest() {
        with(composeTestRule) {
            setContent {
                TrendingItem(stringResource(id = R.string.text_trending),
                    null,
                    "2024-12-29", {}
                )
            }
            onNodeWithText("Trending").assertIsDisplayed()
            onNodeWithText("2024-12-29").assertIsDisplayed()
        }
    }

    @Test
    fun headerTest() {
        with(composeTestRule) {
            setContent {
                Header(titleId = R.string.text_popular) {}
            }
            onNodeWithText("Popular").assertIsDisplayed()
            onNodeWithText("More").assertIsDisplayed()
        }
    }

    @Test
    fun feedsTest() {
        with(composeTestRule) {
            setContent {
                Feeds(
                    feeds = listOf(
                        Movie(
                            anyInt(), anyString(), anyString(), anyString(),
                            anyString(), "title", anyDouble(), anyInt()
                        )
                    ),
                    onFeedClick = {},
                    index = 0
                )
            }
            onNodeWithText("title").assertIsDisplayed()
        }
    }

    @Test
    fun tMDBItemTest() {
        with(composeTestRule) {
            setContent {
                TMDbItem(
                    tmdbItem = Movie(
                        anyInt(), anyString(), anyString(), anyString(),
                        anyString(), "title", anyDouble(), anyInt()
                    ),
                    onFeedClick = {},
                    index = 0
                )
            }
            onNodeWithText("title").assertIsDisplayed()
        }
    }
}