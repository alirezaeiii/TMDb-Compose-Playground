package com.sample.tmdb.common.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.Gender
import com.sample.tmdb.domain.model.Cast
import org.junit.Rule
import org.junit.Test

class PersonCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

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
                    ),
                    navController = rememberNavController(),
                    testPainter = rememberVectorPainter(Icons.Rounded.Man)
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
}
