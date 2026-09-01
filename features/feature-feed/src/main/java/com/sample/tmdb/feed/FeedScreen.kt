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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.ScaffoldState
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.base.BaseViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_220_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_32_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_56_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.TMDbCard
import com.sample.tmdb.common.ui.component.TMDbSwipeRefresh
import com.sample.tmdb.common.ui.theme.Teal200
import com.sample.tmdb.common.ui.theme.TmdbPagingComposeTheme
import com.sample.tmdb.common.utils.TMDbSpacer
import com.sample.tmdb.domain.model.FeedWrapper
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.feed.utils.pagerTransition

@Composable
fun MovieFeedScreen(
    viewModel: MovieFeedViewModel,
    languageViewModel: LanguageViewModel,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    navigate: (FeedNavigationEvent) -> Unit,
    scaffoldState: ScaffoldState,
) {
    FeedScreen(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        navigate = navigate,
        onSearchClicked = onSearchClicked,
        onClick = onClick,
        scaffoldState = scaffoldState,
        commonR.string.movies,
    )
}

@Composable
fun TVShowFeedScreen(
    viewModel: TVShowFeedViewModel,
    languageViewModel: LanguageViewModel,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    navigate: (FeedNavigationEvent) -> Unit,
    scaffoldState: ScaffoldState,
) {
    FeedScreen(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        navigate = navigate,
        onSearchClicked = onSearchClicked,
        onClick = onClick,
        scaffoldState = scaffoldState,
        commonR.string.tv_series,
    )
}

@Composable
private fun FeedScreen(
    viewModel: BaseViewModel<List<FeedWrapper>, Nothing>,
    languageViewModel: LanguageViewModel,
    navigate: (FeedNavigationEvent) -> Unit,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    scaffoldState: ScaffoldState,
    @StringRes resourceId: Int,
) {
    Content(
        viewModel = viewModel,
        languageViewModel = languageViewModel,
        scaffoldState = scaffoldState,
    ) { state, feeds ->
        Box {
            TMDbSwipeRefresh(viewModel, state) {
                FeedCollectionList(feeds, navigate, onClick)
            }
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
fun FeedCollectionList(
    collection: List<FeedWrapper>,
    navigate: (FeedNavigationEvent) -> Unit,
    onFeedClick: (TMDbItem) -> Unit,
) {
    LazyColumn {
        item {
            TMDbSpacer()
        }
        item {
            PagerTMDbItemContainer(
                feedWrapper = collection.first(),
                navigate = navigate,
                onFeedClick = onFeedClick,
            )
        }
        itemsIndexed(collection.drop(1)) { index, feedCollection ->
            Column(modifier = Modifier.padding(top = TMDb_32_dp)) {
                Header(feedCollection, navigate)
                Feeds(feedCollection.feeds, onFeedClick, index)
            }
        }
        item {
            Spacer(
                Modifier
                    .navigationBarsPadding()
                    .windowInsetsTopHeight(WindowInsets(top = TMDb_56_dp)),
            )
        }
    }
}

@Composable
fun PagerTMDbItemContainer(
    feedWrapper: FeedWrapper,
    navigate: (FeedNavigationEvent) -> Unit,
    onFeedClick: (TMDbItem) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { feedWrapper.feeds.size })

    Header(feedWrapper, navigate)
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = Dimens.TMDb_16_dp),
    ) { page ->
        with(feedWrapper.feeds[page]) {
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
                    )
                    .align(Alignment.BottomStart),
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
fun Header(feedWrapper: FeedWrapper, navigate: (FeedNavigationEvent) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .heightIn(min = 36.dp)
            .padding(start = TMDb_12_dp),
    ) {
        Text(
            text = stringResource(id = feedWrapper.sortTypeResourceId),
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
                .clickable(
                    onClick = {
                        moreFeedOnClick(
                            feedWrapper.feeds.first(),
                            feedWrapper.sortType,
                            navigate,
                        )
                    },
                ),
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
            if (index % 3 == 0) {
                TMDbCard(feed, onFeedClick, feed.backdropUrl, TMDb_220_dp)
            } else {
                TMDbCard(feed, onFeedClick, feed.posterUrl, TMDb_120_dp)
            }
        }
    }
}

private fun moreFeedOnClick(item: TMDbItem, sortType: SortType, navigate: (FeedNavigationEvent) -> Unit) {
    val contentType = when (item) {
        is Movie -> ContentType.MOVIE
        is TVShow -> ContentType.TV_SHOW
        else -> throw RuntimeException("Invalid content type: $item")
    }

    navigate(
        FeedNavigationEvent.More(
            contentType = contentType,
            sortType = sortType,
        ),
    )
}

sealed interface FeedNavigationEvent {

    data class More(val contentType: ContentType, val sortType: SortType) : FeedNavigationEvent
}

enum class ContentType {
    MOVIE,
    TV_SHOW,
}

@Preview("default")
@Composable
fun FeedCardPreview() {
    TmdbPagingComposeTheme {
        val movie = Movie(1, "", null, null, null, "Movie", 1.0, 2)
        Feeds(
            feeds = listOf(movie),
            onFeedClick = {},
            0,
        )
    }
}
