package com.android.sample.tmdb.ui.feed

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.FeedWrapper
import com.android.sample.tmdb.domain.model.Movie
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.common.Dimens
import com.android.sample.tmdb.ui.theme.TmdbPagingComposeTheme

@Composable
fun <T : TMDbItem> FeedCollectionList(
    collection: List<FeedWrapper<T>>,
    onFeedClick: (TMDbItem) -> Unit
) {
    LazyColumn {
        itemsIndexed(collection) { index, feedCollection ->
            FeedCollection(
                feedCollection = feedCollection,
                onFeedClick = onFeedClick,
                itemWidth = if (index == 0) 220.dp else 120.dp
            )
        }
    }
}

@Composable
private fun <T : TMDbItem> FeedCollection(
    feedCollection: FeedWrapper<T>,
    onFeedClick: (TMDbItem) -> Unit,
    itemWidth: Dp,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = Dimens.rowHeight)
                .padding(start = Dimens.PaddingMedium)
        ) {
            Text(
                text = stringResource(id = feedCollection.sortTypeResourceId),
                maxLines = 1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = stringResource(R.string.more_item),
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(
                        start = Dimens.PaddingMedium,
                        end = Dimens.PaddingMedium,
                        top = Dimens.PaddingMedium,
                        bottom = Dimens.paddingBottom
                    )
            )
        }
        Feeds(feedCollection.feeds, onFeedClick, itemWidth)
    }
}

@Composable
private fun <T : TMDbItem> Feeds(
    feeds: List<T>,
    onFeedClick: (TMDbItem) -> Unit,
    tmdbItemWidth: Dp,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = Dimens.paddingMicro, end = Dimens.paddingMicro)
    ) {
        items(feeds) { feed ->
            TMDbItem(feed, onFeedClick, tmdbItemWidth)
        }
    }
}

@Composable
private fun <T : TMDbItem> TMDbItem(
    tmdbItem: T,
    onFeedClick: (TMDbItem) -> Unit,
    itemWidth: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = { onFeedClick(tmdbItem) })
            .padding(Dimens.PaddingSmall)
    ) {
        AsyncImage(
            model = tmdbItem.posterUrl,
            contentDescription = null,
            modifier = Modifier
                .size(width = itemWidth, height = 180.dp)
                .border(.3.dp, MaterialTheme.colors.onSurface, RectangleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = tmdbItem.name,
            fontSize = TmdbPagingComposeTheme.fontSizes.sp_11,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .size(width = itemWidth, height = 56.dp)
                .padding(top = Dimens.PaddingSmall)
        )
    }
}

@Preview("default")
@Composable
fun FeedCardPreview() {
    TmdbPagingComposeTheme {
        val movie = Movie(1, "", null, null, null, "Movie", 1.0)
        TMDbItem(
            tmdbItem = movie,
            onFeedClick = {},
            120.dp
        )
    }
}