package com.sample.tmdb.setting

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SettingScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun settingScreenTest() {
        with(composeTestRule) {
            setContent {
                SettingsScreen()
            }
            onNodeWithText(activity.getString(R.string.source_code_github)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.privacy_policy)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.version)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.about)).assertIsDisplayed()
        }
    }

    @Test
    fun settingsGroupItemTest() {
        with(composeTestRule) {
            setContent {
                SettingsGroupItem(
                    listOf(
                        Settings.Info(
                            R.drawable.ic_info,
                            R.string.version,
                            "1.1",
                        ),
                    ),
                )
            }
            onNodeWithText(activity.getString(R.string.version)).assertIsDisplayed()
            onNodeWithText("1.1").assertIsDisplayed()
        }
    }

    @Test
    fun titleText() {
        with(composeTestRule) {
            setContent {
                TitleText(title = "title")
            }
            onNodeWithText("title").assertIsDisplayed()
        }
    }
}
