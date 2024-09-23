package com.sample.tmdb.image

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import com.sample.tmdb.domain.model.TMDbImage
import org.junit.Rule
import org.junit.Test

class ImageScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun posterTest() {
        with(composeTestRule) {
            setContent {
                Poster(TMDbImage("", 5))
            }
            onNodeWithText("5").assertIsDisplayed()
        }
    }

    @Test
    fun blurImageTest() {
        with(composeTestRule) {
            setContent {
                BlurImage("")
            }
            onNodeWithContentDescription(activity.getString(R.string.poster_content_description))
        }
    }
}
