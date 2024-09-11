package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme

@Composable
fun TMDbCard(
    tmdbItem: TMDbItem,
    onFeedClick: (TMDbItem) -> Unit,
    imageUrl: String? = tmdbItem.posterUrl,
    itemWidth: Dp = TMDb_120_dp
) {
    Card(
        modifier = Modifier
            .padding(TMDb_6_dp)
            .clickable(onClick = { onFeedClick(tmdbItem) }),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = tmdbItem.name,
                modifier = Modifier
                    .size(width = itemWidth, height = 180.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = tmdbItem.name,
                fontSize = TmdbPagingComposeTheme.fontSizes.sp_11,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .size(width = itemWidth, height = 36.dp)
                    .wrapContentHeight()
            )
        }
    }
}