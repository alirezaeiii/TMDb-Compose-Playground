package com.android.sample.tmdb.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.sample.tmdb.ui.theme.AlphaNearOpaque
import com.android.sample.tmdb.ui.theme.TmdbPagingComposeTheme

@Composable
fun DestinationBar(title: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque),
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 0.dp
        ) {
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
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = null
                )
            }
        }
        TMDbDivider()
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun PreviewDestinationBar() {
    TmdbPagingComposeTheme() {
        DestinationBar("Hello")
    }
}