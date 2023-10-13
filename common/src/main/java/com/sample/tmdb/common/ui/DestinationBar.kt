package com.sample.tmdb.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.MainDestinations
import com.sample.tmdb.common.model.TMDbType
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque

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
            IconButton(
                onClick = { upPress?.invoke() },
                modifier = Modifier
                    .alpha(if (upPress == null) 0f else 1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
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
            IconButton(
                onClick = {
                    when (type) {
                        TMDbType.MOVIES -> navController?.navigate(MainDestinations.TMDB_SEARCH_MOVIE_ROUTE)
                        TMDbType.TV_SERIES -> navController?.navigate(MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE)
                        else -> {}
                    }
                },
                modifier = Modifier
                    .alpha(if (type == null) 0f else 1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = type?.let {
                        stringResource(
                            id = R.string.search,
                            it.name
                        )
                    }
                )
            }
        }
        TMDbDivider()
    }
}