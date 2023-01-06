package com.android.sample.tmdb.ui.paging

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.ui.common.ErrorScreen
import com.android.sample.tmdb.ui.common.LoadingItem
import com.android.sample.tmdb.ui.common.LoadingView
import com.android.sample.tmdb.ui.paging.movie.*
import com.android.sample.tmdb.ui.paging.tvshow.*

@Composable
fun TrendingMovieScreen(
    viewModel: TrendingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun PopularMovieScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun NowPlayingMovieScreen(
    viewModel: NowPlayingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun UpcomingMovieScreen(
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TopRatedMovieScreen(
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TrendingTVShowScreen(
    viewModel: TrendingTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun PopularTVShowScreen(
    viewModel: PopularTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun AiringTodayTVShowScreen(
    viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun OnTheAirTVShowScreen(
    viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun TopRatedTVShowScreen(
    viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
) {
    PagingScreen(viewModel = viewModel)
}

@Composable
fun <T : TMDbItem> PagingScreen(viewModel: BasePagingViewModel<T>) {
    val lazyTMDbItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyTMDbItems) {
            it?.let { tmdbItem ->
                TMDbItem(item = tmdbItem)
            }
        }

        lazyTMDbItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyTMDbItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorScreen(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            refresh = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyTMDbItems.loadState.append as LoadState.Error
                    item {
                        ErrorScreen(
                            message = e.error.localizedMessage!!,
                            refresh = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun <T : TMDbItem> TMDbItem(item: T) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TMDbItemTitle(
            item.name,
            modifier = Modifier.weight(1f)
        )
        item.posterUrl?.let {
            TMDbItemImage(
                it,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(90.dp)
            )
        }
    }
}

@Composable
fun TMDbItemImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )

}

@Composable
fun TMDbItemTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}