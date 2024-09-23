package com.sample.tmdb.common.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun errorScreenTest() {
        with(composeTestRule) {
            setContent {
                ErrorScreen("Message") {
                }
            }
            onNodeWithText("Message").assertIsDisplayed()
            onNodeWithText(
                activity.getString(
                    R.string.retry,
                ),
            ).assertIsDisplayed()
        }
    }
}
