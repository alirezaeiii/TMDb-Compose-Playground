package com.sample.tmdb.bookmark

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.domain.model.Movie
import org.junit.Rule
import org.junit.Test

class BookmarkScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun bookmarkScreenTest() {
        with(composeTestRule) {
            setContent {
                TabContent(
                    items =
                        listOf(
                            Movie(
                                1,
                                "overview",
                                "releaseDate",
                                null,
                                null,
                                "name",
                                5.0,
                                5,
                            ),
                        ),
                ) {
                }
            }
            onNodeWithText("releaseDate").assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("5.0").assertIsDisplayed()
            onNodeWithText("5").assertIsDisplayed()
        }
    }

    @Test
    fun emptyViewTest() {
        with(composeTestRule) {
            setContent {
                EmptyView(R.string.movie)
            }
            onNodeWithContentDescription(activity.getString(R.string.empty_list)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.empty_list, activity.getString(R.string.movie)))
        }
    }
}
