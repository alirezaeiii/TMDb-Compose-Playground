package com.sample.tmdb.paging.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.sample.tmdb.common.R
import com.sample.tmdb.paging.R as pagingR
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun searchBarWithQueryTest() {
        with(composeTestRule) {
            setContent {
                SearchBar(
                    query = "query",
                    resourceId = R.string.movies,
                    onQueryChange = {},
                    searchFocused = true,
                    onSearchFocusChange = {},
                    onClearQuery = {})
            }
            onNodeWithText("query").assertIsDisplayed()
        }
    }

    @Test
    fun emptySearchBarTest() {
        with(composeTestRule) {
            setContent {
                SearchBar(
                    query = "",
                    resourceId = R.string.movies,
                    onQueryChange = {},
                    searchFocused = true,
                    onSearchFocusChange = {},
                    onClearQuery = {})
            }
            onNodeWithText(
                activity.getString(
                    pagingR.string.search,
                    activity.getString(R.string.movies)
                )
            ).assertIsDisplayed()
        }
    }

    @Test
    fun searchHintTest() {
        with(composeTestRule) {
            setContent {
                SearchHint(R.string.movies)
            }
            onNodeWithContentDescription(activity.getString(pagingR.string.search_desc)).assertIsDisplayed()
            onNodeWithText(
                activity.getString(
                    pagingR.string.search,
                    activity.getString(R.string.movies)
                )
            ).assertIsDisplayed()
        }
    }
}