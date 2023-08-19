package com.sample.tmdb.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.TMDbType
import com.sample.tmdb.ui.MainDestinations
import com.sample.tmdb.ui.theme.AlphaNearOpaque

@Composable
fun DestinationBar(
    modifier: Modifier = Modifier,
    title: String,
    navController: NavController? = null,
    type: TMDbType? = null,
    upPress: (() -> Unit)? = null,
) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque),
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 0.dp
        ) {
            upPress?.let {
                IconButton(
                    onClick = { it.invoke() },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            type?.let {
                IconButton(
                    onClick = {
                        when (it) {
                            TMDbType.MOVIES -> navController?.navigate(MainDestinations.TMDB_SEARCH_MOVIE_ROUTE)
                            TMDbType.TV_SERIES -> navController?.navigate(MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Search"
                    )
                }
            }
        }
        TMDbDivider()
    }
}