package com.sample.tmdb.credit

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.sample.tmdb.common.model.Gender
import com.sample.tmdb.common.model.placeholderIcon
import com.sample.tmdb.domain.model.Cast
import org.junit.Rule
import org.junit.Test
import com.sample.tmdb.common.R as commonR

class CreditScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun creditScreenTest() {
        with(composeTestRule) {
            val person = Cast("role", "name", null, Gender.MALE, 1)
            setContent {
                CreditScreen(
                    R.string.biography,
                    rememberNavController(),
                    listOf(person),
                    rememberVectorPainter(person.gender.placeholderIcon),
                )
            }
            onNodeWithText(activity.getString(R.string.biography)).assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("role").assertIsDisplayed()
            onNodeWithContentDescription(
                activity.getString(
                    commonR.string.person_content_description,
                    "name",
                    "role",
                ),
            ).assertIsDisplayed()
        }
    }
}
