package com.android.sample.tmdb.ui.paging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.android.sample.tmdb.domain.model.TMDbItem
import com.android.sample.tmdb.domain.model.TMDbType
import com.android.sample.tmdb.ui.common.*
import com.android.sample.tmdb.ui.theme.imageTint
import com.android.sample.tmdb.ui.theme.rateColors
import com.android.sample.tmdb.utils.toDp

private const val COLUMN_COUNT = 2

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(COLUMN_COUNT) }

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
    upPress: () -> Unit,
    navController: NavController,
    type: TMDbType,
    title: String,
) {
    Box {
        PagingScreen(viewModel, onClick)
        DestinationBar(title = title, upPress = upPress, navController = navController, type = type)
    }
}

@Composable
fun <T : TMDbItem> PagingScreen(
    viewModel: BasePagingViewModel<T>,
    onClick: (TMDbItem) -> Unit,
) {
    val lazyTMDbItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    when (lazyTMDbItems.loadState.refresh) {
        is LoadState.Loading -> {
            TMDbProgressBar()
        }
        is LoadState.Error -> {
            val message =
                (lazyTMDbItems.loadState.refresh as? LoadState.Error)?.error?.message ?: return

            lazyTMDbItems.apply {
                ErrorScreen(
                    message = message,
                    modifier = Modifier.fillMaxSize(),
                    refresh = { retry() }
                )
            }
        }
        else -> {
            LazyTMDbItemGrid(lazyTMDbItems, onClick)
        }
    }
}


@Composable
private fun <T : TMDbItem> LazyTMDbItemGrid(
    lazyTMDbItems: LazyPagingItems<T>,
    onClick: (TMDbItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN_COUNT),
        contentPadding = PaddingValues(
            start = Dimens.GridSpacing,
            end = Dimens.GridSpacing,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.GridSpacing
                )
        ),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.GridSpacing,
            Alignment.CenterHorizontally
        ),
        content = {

            repeat(COLUMN_COUNT) {
                item {
                    Spacer(
                        Modifier.windowInsetsTopHeight(
                            WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                        )
                    )
                }
            }

            items(lazyTMDbItems.itemCount) { index ->
                val tmdbItem = lazyTMDbItems[index]
                tmdbItem?.let {
                    TMDbItemContent(
                        it,
                        Modifier
                            .height(320.dp)
                            .padding(vertical = Dimens.GridSpacing),
                        onClick
                    )
                }
            }

            lazyTMDbItems.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item(span = span) {
                            LoadingRow(modifier = Modifier.padding(vertical = Dimens.GridSpacing))
                        }
                    }
                    is LoadState.Error -> {
                        val message =
                            (loadState.append as? LoadState.Error)?.error?.message ?: return@apply

                        item(span = span) {
                            ErrorScreen(
                                message = message,
                                modifier = Modifier.padding(vertical = Dimens.GridSpacing),
                                refresh = { retry() })
                        }
                    }
                    else -> {}
                }
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : TMDbItem> TMDbItemContent(
    tmdbItem: T,
    modifier: Modifier = Modifier,
    onClick: (TMDbItem) -> Unit
) {
    Box(modifier = modifier) {
        TMDbItemRate(
            tmdbItem.voteAverage,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(2f)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 12.dp),
            shape = RoundedCornerShape(size = 8.dp),
            elevation = 8.dp,
            onClick = { onClick.invoke(tmdbItem) }
        ) {
            Box {
                TMDbItemPoster(tmdbItem.posterUrl, tmdbItem.name)
                TMDbItemInfo(
                    tmdbItem,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color(0x97000000))
                )
            }
        }
    }
}

@Composable
private fun TMDbItemRate(rate: Double, modifier: Modifier) {
    val shape = RoundedCornerShape(percent = 50)
    Surface(
        shape = shape,
        elevation = 12.dp,
        modifier = modifier
    ) {
        Text(
            text = rate.toString(),
            style = MaterialTheme.typography.body1.copy(color = Color.White),
            modifier = Modifier
                .background(Brush.horizontalGradient(Color.rateColors(movieRate = rate)))
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
private fun BoxScope.TMDbItemPoster(posterUrl: String?, tmdbItemName: String) {
    val painter = rememberAsyncImagePainter(
        model = posterUrl,
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(Icons.Default.Movie)
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error -> ColorFilter.tint(
            MaterialTheme.colors.imageTint
        )
        else -> null
    }
    val scale =
        if (painter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

    Image(
        painter = painter,
        colorFilter = colorFilter,
        contentDescription = tmdbItemName,
        contentScale = scale,
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
    )
}

@Composable
private fun <T : TMDbItem> TMDbItemInfo(tmdbItem: T, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        TMDbItemName(name = tmdbItem.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            tmdbItem.releaseDate?.let { TMDbItemFeature(Icons.Default.DateRange, it) }
            TMDbItemFeature(Icons.Default.ThumbUp, tmdbItem.voteCount.toString())
        }
    }
}

@Composable
private fun TMDbItemName(name: String) = Text(
    text = name,
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.White,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
)

@Composable
private fun TMDbItemFeature(icon: ImageVector, field: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(13.dp)
        )
        Text(
            text = field,
            style = MaterialTheme.typography.subtitle2.copy(
                color = Color.White,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}