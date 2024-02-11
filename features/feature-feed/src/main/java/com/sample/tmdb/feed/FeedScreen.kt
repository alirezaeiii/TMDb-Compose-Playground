package com.sample.tmdb.feed

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.TMDbCard
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.common.R as R1

@Composable
fun MovieFeedScreen(
    onClick: (TMDbItem) -> Unit,
    onSearchMovie: () -> Unit,
    navController: NavController,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = onSearchMovie,
        onClick = onClick,
        R1.string.movies
    )
}

@Composable
fun TVShowFeedScreen(
    onClick: (TMDbItem) -> Unit,
    onSearchTVShow:()->Unit,
    navController: NavController,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = onSearchTVShow,
        onClick = onClick,
        R1.string.tv_series
    )
}

@Composable
private inline fun <reified T : TMDbItem> FeedScreen(
    viewModel: BaseFeedViewModel<T>,
    navController: NavController,
    noinline onSearchClicked: () -> Unit,
    noinline onClick: (TMDbItem) -> Unit,
    @StringRes resourceId: Int

) {
    Content(viewModel = viewModel) { feeds ->
        Box {
            FeedCollectionList(navController, feeds, onClick)
            DestinationBar(
                title = stringResource(
                    R.string.app_title, stringResource(resourceId)
                ), onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
private inline fun <reified T : TMDbItem> FeedCollectionList(
    navController: NavController,
    collection: List<FeedWrapper<T>>,
    noinline onFeedClick: (TMDbItem) -> Unit
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
                navController = navController,
                feedCollection = feedCollection,
                onFeedClick = onFeedClick,
                index = index
            )
        }
    }
}

@Composable
private inline fun <reified T : TMDbItem> FeedCollection(
    navController: NavController,
    feedCollection: FeedWrapper<T>,
    noinline onFeedClick: (TMDbItem) -> Unit,
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
                        when (T::class) {
                            Movie::class -> {
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

                            TVShow::class -> {
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
    TMDbCard(tmdbItem, onFeedClick, imageUrl, itemWidth)
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