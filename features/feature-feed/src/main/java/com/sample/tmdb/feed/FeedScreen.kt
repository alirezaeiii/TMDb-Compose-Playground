package com.sample.tmdb.feed

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_32_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.TMDbCard
import com.sample.tmdb.common.ui.theme.Teal200
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feed.utils.pagerTransition
import com.sample.tmdb.common.R as R1

@Composable
fun MovieFeedScreen(navController: NavController, viewModel: MovieFeedViewModel = hiltViewModel()) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = { navController.navigate(MainDestinations.TMDB_SEARCH_MOVIE_ROUTE) },
        onClick = { navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/${it.id}") },
        R1.string.movies,
    )
}

@Composable
fun TVShowFeedScreen(navController: NavController, viewModel: TVShowFeedViewModel = hiltViewModel()) {
    FeedScreen(
        viewModel = viewModel,
        navController = navController,
        onSearchClicked = { navController.navigate(MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE) },
        onClick = { navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/${it.id}") },
        R1.string.tv_series,
    )
}

@Composable
private fun <T : TMDbItem> FeedScreen(
    viewModel: BaseFeedViewModel<T>,
    navController: NavController,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    @StringRes resourceId: Int,
) {
    Content(viewModel = viewModel) { feeds ->
        Box {
            FeedCollectionList(navController, feeds, onClick)
            DestinationBar(
                title =
                stringResource(
                    R.string.app_title,
                    stringResource(resourceId),
                ),
                onSearchClicked = onSearchClicked,
            )
        }
    }
}

@Composable
fun FeedCollectionList(navController: NavController, collection: List<FeedWrapper>, onFeedClick: (TMDbItem) -> Unit) {
    LazyColumn {
        item {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp)),
                ),
            )
        }
        item {
            PagerTMDbItemContainer(
                item = collection.first(),
                navController = navController,
                onFeedClick = onFeedClick,
            )
        }
        itemsIndexed(collection.drop(1)) { index, feedCollection ->
            FeedCollection(
                navController = navController,
                feedCollection = feedCollection,
                onFeedClick = onFeedClick,
                index = index,
            )
        }
        item {
            Spacer(
                Modifier
                    .navigationBarsPadding()
                    .windowInsetsTopHeight(WindowInsets(top = 56.dp)),
            )
        }
    }
}

@Composable
fun PagerTMDbItemContainer(item: FeedWrapper, navController: NavController, onFeedClick: (TMDbItem) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { item.feeds.size })

    Header(titleId = item.sortTypeResourceId) {
        when (item.feeds.first()) {
            is Movie -> {
                when (item.sortType) {
                    SortType.TRENDING -> navController.navigate(MainDestinations.TMDB_TRENDING_MOVIES_ROUTE)
                    else -> throw RuntimeException("Movie pager Sort type is not valid")
                }
            }

            is TVShow -> {
                when (item.sortType) {
                    SortType.TRENDING -> navController.navigate(MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE)
                    else -> throw RuntimeException("TV show pager item Sort type is not valid")
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = Dimens.TMDb_16_dp),
    ) { page ->
        with(item.feeds[page]) {
            TrendingItem(
                modifier =
                Modifier.pagerTransition(
                    pagerState = pagerState,
                    page = page,
                ),
                title = name,
                imageUrl = backdropUrl,
                releaseDate = releaseDate,
                onClick = { onFeedClick(this) },
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colors.primary else Teal200
            Box(
                modifier =
                Modifier
                    .padding(Dimens.TMDb_4_dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(TMDb_6_dp),
            )
        }
    }
}

@Composable
fun TrendingItem(
    title: String,
    imageUrl: String?,
    releaseDate: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier =
        modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(10.dp))
            .then(Modifier.clickable(onClick = onClick)),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier =
                Modifier
                    .padding(
                        start = TMDb_12_dp,
                        bottom = TMDb_6_dp,
                    ).align(Alignment.BottomStart),
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(TMDb_6_dp))
                releaseDate?.let { releaseDate ->
                    Text(
                        text = releaseDate,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedCollection(
    navController: NavController,
    feedCollection: FeedWrapper,
    onFeedClick: (TMDbItem) -> Unit,
    index: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(top = TMDb_32_dp)) {
        Header(titleId = feedCollection.sortTypeResourceId) {
            when (feedCollection.feeds.first()) {
                is Movie -> {
                    when (feedCollection.sortType) {
                        SortType.MOST_POPULAR -> navController.navigate(MainDestinations.TMDB_POPULAR_MOVIES_ROUTE)
                        SortType.NOW_PLAYING -> navController.navigate(MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE)
                        SortType.UPCOMING -> navController.navigate(MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE)
                        SortType.HIGHEST_RATED ->
                            navController.navigate(
                                MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE,
                            )

                        SortType.DISCOVER -> navController.navigate(MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE)
                        else -> throw RuntimeException("Movie feed item Sort type is not valid")
                    }
                }

                is TVShow -> {
                    when (feedCollection.sortType) {
                        SortType.MOST_POPULAR -> navController.navigate(MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE)
                        SortType.NOW_PLAYING -> navController.navigate(MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE)
                        SortType.UPCOMING -> navController.navigate(MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE)
                        SortType.HIGHEST_RATED ->
                            navController.navigate(
                                MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE,
                            )

                        SortType.DISCOVER -> navController.navigate(MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE)
                        else -> throw RuntimeException("TV show feed item Sort type is not valid")
                    }
                }
            }
        }
        Feeds(feedCollection.feeds, onFeedClick, index)
    }
}

@Composable
fun Header(@StringRes titleId: Int, onMoreClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .heightIn(min = 36.dp)
            .padding(start = TMDb_12_dp),
    ) {
        Text(
            text = stringResource(id = titleId),
            maxLines = 1,
            color = MaterialTheme.colors.onSurface,
            modifier =
            Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start),
        )
        Text(
            text = stringResource(R.string.more_item),
            color = MaterialTheme.colors.onSurface,
            modifier =
            Modifier
                .align(Alignment.CenterVertically)
                .padding(TMDb_12_dp)
                .clickable(onClick = onMoreClick),
        )
    }
}

@Composable
fun Feeds(feeds: List<TMDbItem>, onFeedClick: (TMDbItem) -> Unit, index: Int, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = TMDb_2_dp, end = TMDb_2_dp),
    ) {
        items(feeds) { feed ->
            TMDbItem(feed, onFeedClick, index)
        }
    }
}

@Composable
fun TMDbItem(tmdbItem: TMDbItem, onFeedClick: (TMDbItem) -> Unit, index: Int) {
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
            0,
        )
    }
}
