package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.sample.tmdb.common.R
import com.sample.tmdb.common.ui.Dimens.TMDb_0_dp
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque

@Composable
fun DestinationBar(
    modifier: Modifier = Modifier,
    title: String,
    onSearchClicked: (() -> Unit)? = null,
    upPress: (() -> Unit)? = null,
) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque),
            contentColor = MaterialTheme.colors.onSurface,
            elevation = TMDb_0_dp,
        ) {
            IconButton(
                onClick = { upPress?.invoke() },
                modifier =
                Modifier
                    .alpha(if (upPress == null) 0f else 1f)
                    .align(Alignment.CenterVertically),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
            )
            IconButton(
                onClick = { onSearchClicked?.invoke() },
                modifier =
                Modifier
                    .alpha(if (onSearchClicked == null) 0f else 1f)
                    .align(Alignment.CenterVertically),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search_desc),
                )
            }
        }
        TMDbDivider()
    }
}
