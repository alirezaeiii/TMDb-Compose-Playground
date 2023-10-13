package com.sample.tmdb.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.theme.imageTint
import com.sample.tmdb.common.ui.theme.rateColors

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
        modifier = modifier.padding(
            horizontal = Dimens.PaddingSmall,
            vertical = Dimens.PaddingExtraSmall
        )
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
            modifier = Modifier.padding(horizontal = Dimens.PaddingMicro)
        )
    }
}