package com.sample.tmdb.common.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.utils.Gender
import com.sample.tmdb.common.utils.placeholderIcon
import org.junit.Rule
import org.junit.Test

class PersonCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun personCardTest() {
        with(composeTestRule) {
            val person = TrendingPerson(1, "name", "subtitle", Gender.MALE, null)
            setContent {
                PersonCard(
                    person = person,
                    {},
                    testPainter = rememberVectorPainter(person.gender.placeholderIcon),
                )
            }
            onNodeWithContentDescription(
                activity.getString(
                    R.string.person_content_description,
                    "name",
                    "subtitle",
                ),
            ).assertIsDisplayed()
            onNodeWithText("subtitle").assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()
        }
    }
}
