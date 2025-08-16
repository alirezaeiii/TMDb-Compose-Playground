package com.sample.tmdb.common.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

class PersonCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /*
    @Test
    fun personCardTest() {
        with(composeTestRule) {
            setContent {
                PersonCard(
                    person =
                    Cast(
                        "role",
                        "name",
                        null,
                        Gender.MALE,
                        1,
                    )
                )
            }
            onNodeWithContentDescription(
                activity.getString(
                    R.string.person_content_description,
                    "name",
                    "role",
                ),
            ).assertIsDisplayed()
            onNodeWithText("role").assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()
        }
    }
     */
}
