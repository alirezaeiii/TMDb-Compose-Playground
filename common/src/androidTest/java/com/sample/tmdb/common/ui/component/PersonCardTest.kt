package com.sample.tmdb.common.ui.component

import android.os.Parcel
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.Gender
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
                    person = CreditSample(
                        "role", "name", null,
                        Gender.MALE, 1
                    ),
                    navController = rememberNavController()
                )
            }
            onNodeWithContentDescription(
                activity.getString(
                    R.string.person_content_description,
                    "name",
                    "role"
                )
            ).assertIsDisplayed()
            onNodeWithText("role").assertIsDisplayed()
            onNodeWithText("name").assertIsDisplayed()

        }
    }
}

private class CreditSample(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int
) : Credit {
    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel, p1: Int) {
    }
}