package com.sample.tmdb.credit

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.TYPE
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.utils.Gender
import com.sample.tmdb.common.utils.placeholderIcon
import org.junit.Rule
import org.junit.Test

class CreditScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun creditScreenTest() {
        with(composeTestRule) {
            val person = TrendingPerson(1, "name", "role", Gender.MALE, null)
            setContent {
                CreditScreen(
                    R.string.movies,
                    {},
                    {},
                    listOf(person),
                    TYPE.CREDIT,
                    {},
                    rememberVectorPainter(person.gender.placeholderIcon),
                )
            }
            onNodeWithText(activity.getString(R.string.movies)).assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()
            onNodeWithText("role").assertIsDisplayed()
            onNodeWithContentDescription(
                activity.getString(
                    R.string.person_content_description,
                    "name",
                    "role",
                ),
            ).assertIsDisplayed()
        }
    }
}
