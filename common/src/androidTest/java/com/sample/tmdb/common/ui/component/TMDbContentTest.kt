package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.domain.model.Movie
import org.junit.Rule
import org.junit.Test

class TMDbContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tmdbContentTest() {
        with(composeTestRule) {
            setContent {
                TMDbContent(
                    tmdbItem =
                    Movie(
                        1,
                        "overview",
                        "releaseDate",
                        null,
                        null,
                        "name",
                        1.1,
                        1,
                    ),
                ) {
                }
            }
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("releaseDate").assertIsDisplayed()
            onNodeWithText("1.1").assertIsDisplayed()
            onNodeWithText("1").assertIsDisplayed()
            onNodeWithContentDescription("name").assertIsDisplayed()
        }
    }

    @Test
    fun tmdbItemRate() {
        with(composeTestRule) {
            setContent {
                TMDbItemRate(1.1)
            }
            onNodeWithText("1.1").assertIsDisplayed()
        }
    }

    @Test
    fun tmdbItemPosterTest() {
        with(composeTestRule) {
            setContent {
                Box {
                    TMDbItemPoster(null, "name")
                }
            }
            onNodeWithContentDescription("name").assertIsDisplayed()
        }
    }

    @Test
    fun tmdbItemInfo() {
        with(composeTestRule) {
            setContent {
                TMDbItemInfo(
                    tmdbItem =
                    Movie(
                        1,
                        "overview",
                        "releaseDate",
                        null,
                        null,
                        "name",
                        1.1,
                        1,
                    ),
                )
            }
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("releaseDate").assertIsDisplayed()
            onNodeWithText("1").assertIsDisplayed()
        }
    }

    @Test
    fun tmdbItemName() {
        with(composeTestRule) {
            setContent {
                TMDbItemName("name")
            }
            onNodeWithText("name").assertIsDisplayed()
        }
    }

    @Test
    fun tmdbItemFeature() {
        with(composeTestRule) {
            setContent {
                TMDbItemFeature(icon = Icons.Default.DateRange, field = "releaseDate")
            }
            onNodeWithText("releaseDate").assertIsDisplayed()
        }
    }
}
