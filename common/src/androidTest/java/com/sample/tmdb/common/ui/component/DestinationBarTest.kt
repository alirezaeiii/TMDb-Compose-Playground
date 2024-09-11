package com.sample.tmdb.common.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import org.junit.Rule
import org.junit.Test

class DestinationBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun destinationBarTest() {
        with(composeTestRule) {
            setContent {
                DestinationBar(Modifier,"title", {}, {})
            }
            onNodeWithText("title").assertIsDisplayed()
            onNodeWithContentDescription(
                activity.getString(
                    R.string.back
                )
            ).assertIsDisplayed()
            onNodeWithContentDescription(
                activity.getString(
                    R.string.search_desc
                )
            ).assertIsDisplayed()
        }
    }
}