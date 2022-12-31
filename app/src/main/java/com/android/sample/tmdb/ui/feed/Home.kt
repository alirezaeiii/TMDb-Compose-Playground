package com.android.sample.tmdb.ui.feed

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.android.sample.tmdb.ui.HomeSections

@Composable
fun TMDbBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = tabs.first { it.route == currentRoute }

    BottomNavigation {
        tabs.forEach { section ->
            val selected = section == currentSection

            BottomNavigationItem(
                label = {
                    Text(text = section.title)
                },
                icon = {
                    Icon(
                        painterResource(id = section.icon),
                        contentDescription = section.title
                    )
                },
                selected = selected,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = { navigateToRoute(section.route) }
            )
        }
    }
}