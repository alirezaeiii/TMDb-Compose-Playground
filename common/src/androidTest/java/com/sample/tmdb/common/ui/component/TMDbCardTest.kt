package com.sample.tmdb.common.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.domain.model.Movie
import org.junit.Rule
import org.junit.Test

class TMDbCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tmdbCardTest() {
        with(composeTestRule) {
            setContent {
                TMDbCard(
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
                    {},
                )
            }
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithContentDescription("name").assertIsDisplayed()
        }
    }
}
