package com.sample.tmdb.detail

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.domain.model.MovieDetails
import com.sample.tmdb.domain.model.TMDbImage
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import com.sample.tmdb.common.R as commonR

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun posterTest() {
        with(composeTestRule) {
            setContent {
                Poster(posterUrl = anyString(), tmdbItemName = "title")
            }
            onNodeWithContentDescription("title").assertIsDisplayed()
        }
    }

    @Test
    fun tMDbItemFieldsTest() {
        with(composeTestRule) {
            setContent {
                TMDbItemFields(
                    MovieDetails(
                        anyString(),
                        anyList(),
                        anyString(),
                        anyInt(),
                        anyString(),
                        anyString(),
                        anyString(),
                        anyDouble(),
                        anyString(),
                        "2024-12-29",
                        anyList(),
                        anyString(),
                        anyString(),
                        anyString(),
                        1.0,
                        1,
                    ),
                )
            }
            onNodeWithText(activity.getString(R.string.release_date)).assertIsDisplayed()
            onNodeWithText("2024-12-29").assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.vote_average)).assertIsDisplayed()
            onNodeWithText("1.0").assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.votes)).assertIsDisplayed()
            onNodeWithText("1").assertIsDisplayed()
        }
    }

    @Test
    fun tMDbItemFieldTest() {
        with(composeTestRule) {
            setContent {
                TMDbItemField("name", "value")
            }
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("value").assertIsDisplayed()
        }
    }

    @Test
    fun imageSectionTest() {
        with(composeTestRule) {
            setContent {
                ImageSection(image = TMDbImage(anyString(), anyInt())) {}
            }
            onNodeWithContentDescription(activity.getString(commonR.string.poster_content_description))
                .assertIsDisplayed()
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun toggleBookmarkFabTest() {
        with(composeTestRule) {
            setContent {
                ToggleBookmarkFab(isBookmark = true, isVisible = mutableStateOf(true)) {}
            }
            onNodeWithContentDescription(activity.getString(R.string.favorite)).assertIsDisplayed()
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Test
    fun toggleUnBookmarkFabTest() {
        with(composeTestRule) {
            setContent {
                ToggleBookmarkFab(isBookmark = false, isVisible = mutableStateOf(true)) {}
            }
            onNodeWithContentDescription(activity.getString(R.string.un_favorite)).assertIsDisplayed()
        }
    }
}
