package com.sample.tmdb.ui.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.*
import com.sample.tmdb.ui.MainDestinations
import com.sample.tmdb.ui.common.Dimens
import com.sample.tmdb.ui.theme.TmdbPagingComposeTheme

@Composable
fun <T : TMDbItem> FeedCollectionList(
    tmdbType: TMDbType,
    navController: NavController,
    collection: List<FeedWrapper<T>>,
    onFeedClick: (TMDbItem) -> Unit
) {
    LazyColumn {
        item {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
        }
        itemsIndexed(collection) { index, feedCollection ->
            FeedCollection(
                tmdbType = tmdbType,
                navController = navController,
                feedCollection = feedCollection,
                onFeedClick = onFeedClick,
                index = index
            )
        }
    }
}

@Composable
private fun <T : TMDbItem> FeedCollection(
    tmdbType: TMDbType,
    navController: NavController,
    feedCollection: FeedWrapper<T>,
    onFeedClick: (TMDbItem) -> Unit,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 36.dp)
                .padding(start = Dimens.PaddingNormal)
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
                    .clickable {
                        when (tmdbType) {
                            TMDbType.MOVIES -> {
                                when (feedCollection.sortType) {
                                    SortType.TRENDING -> {
                                        navController.navigate(MainDestinations.TMDB_TRENDING_MOVIES_ROUTE)
                                    }

                                    SortType.MOST_POPULAR -> {
                                        navController.navigate(MainDestinations.TMDB_POPULAR_MOVIES_ROUTE)
                                    }

                                    SortType.NOW_PLAYING -> {
                                        navController.navigate(MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE)
                                    }

                                    SortType.UPCOMING -> {
                                        navController.navigate(MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE)
                                    }

                                    SortType.HIGHEST_RATED -> {
                                        navController.navigate(MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE)
                                    }

                                    SortType.DISCOVER -> {
                                        navController.navigate(MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE)
                                    }
                                }
                            }

                            TMDbType.TV_SERIES -> {
                                when (feedCollection.sortType) {
                                    SortType.TRENDING -> {
                                        navController.navigate(MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE)
                                    }

                                    SortType.MOST_POPULAR -> {
                                        navController.navigate(MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE)
                                    }

                                    SortType.NOW_PLAYING -> {
                                        navController.navigate(MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE)
                                    }

                                    SortType.UPCOMING -> {
                                        navController.navigate(MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE)
                                    }

                                    SortType.HIGHEST_RATED -> {
                                        navController.navigate(MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE)
                                    }

                                    SortType.DISCOVER -> {
                                        navController.navigate(MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE)
                                    }
                                }
                            }
                        }
                    }
                    .padding(Dimens.PaddingNormal)
            )
        }
        Feeds(feedCollection.feeds, onFeedClick, index)
    }
}

@Composable
private fun <T : TMDbItem> Feeds(
    feeds: List<T>,
    onFeedClick: (TMDbItem) -> Unit,
    index: Int,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = Dimens.PaddingMicro, end = Dimens.PaddingMicro)
    ) {
        items(feeds) { feed ->
            TMDbItem(feed, onFeedClick, index)
        }
    }
}

@Composable
private fun <T : TMDbItem> TMDbItem(
    tmdbItem: T,
    onFeedClick: (TMDbItem) -> Unit,
    index: Int
) {
    val itemWidth: Dp
    val imageUrl: String?
    if (index % 3 == 0) {
        itemWidth = 220.dp
        imageUrl = tmdbItem.backdropUrl
    } else {
        itemWidth = 120.dp
        imageUrl = tmdbItem.posterUrl
    }
    Card(
        modifier = Modifier
            .padding(Dimens.PaddingSmall)
            .clickable(onClick = { onFeedClick(tmdbItem) }),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
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

@Preview("default")
@Composable
fun FeedCardPreview() {
    TmdbPagingComposeTheme {
        val movie = Movie(1, "", null, null, null, "Movie", 1.0, 2)
        TMDbItem(
            tmdbItem = movie,
            onFeedClick = {},
            0
        )
    }
}