package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_4_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.theme.imageTint
import com.sample.tmdb.common.ui.theme.rateColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : TMDbItem> TMDbContent(
    tmdbItem: T,
    modifier: Modifier = Modifier,
    onClick: (TMDbItem) -> Unit,
) {
    Box(modifier = modifier) {
        TMDbItemRate(
            tmdbItem.voteAverage,
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(2f),
        )
        Card(
            modifier =
                Modifier
                    .fillMaxSize()
                    .offset(y = TMDb_12_dp),
            shape = RoundedCornerShape(size = TMDb_8_dp),
            elevation = TMDb_8_dp,
            onClick = { onClick.invoke(tmdbItem) },
        ) {
            Box {
                TMDbItemPoster(tmdbItem.posterUrl, tmdbItem.name)
                TMDbItemInfo(
                    tmdbItem,
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color(0x97000000)),
                )
            }
        }
    }
}

@Composable
fun TMDbItemRate(
    rate: Double,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(percent = 50)
    Surface(
        shape = shape,
        elevation = TMDb_12_dp,
        modifier = modifier,
    ) {
        Text(
            text = rate.toString(),
            style = MaterialTheme.typography.body1.copy(color = Color.White),
            modifier =
                Modifier
                    .background(Brush.horizontalGradient(Color.rateColors(movieRate = rate)))
                    .padding(horizontal = 10.dp),
        )
    }
}

@Composable
fun BoxScope.TMDbItemPoster(
    posterUrl: String?,
    tmdbItemName: String,
) {
    val painter =
        rememberAsyncImagePainter(
            model = posterUrl,
            error = rememberVectorPainter(Icons.Filled.BrokenImage),
            placeholder = rememberVectorPainter(Icons.Default.Movie),
        )
    val colorFilter =
        when (painter.state) {
            is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error ->
                ColorFilter.tint(
                    MaterialTheme.colors.imageTint,
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
        modifier =
            Modifier
                .fillMaxSize()
                .align(Alignment.Center),
    )
}

@Composable
fun <T : TMDbItem> TMDbItemInfo(
    tmdbItem: T,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(TMDb_4_dp),
        modifier =
            modifier.padding(
                horizontal = TMDb_6_dp,
                vertical = TMDb_4_dp,
            ),
    ) {
        TMDbItemName(name = tmdbItem.name)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            tmdbItem.releaseDate?.let { TMDbItemFeature(Icons.Default.DateRange, it) }
            TMDbItemFeature(Icons.Default.ThumbUp, tmdbItem.voteCount.toString())
        }
    }
}

@Composable
fun TMDbItemName(name: String) =
    Text(
        text = name,
        style =
            MaterialTheme.typography.subtitle1.copy(
                color = Color.White,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W500,
            ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )

@Composable
fun TMDbItemFeature(
    icon: ImageVector,
    field: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(13.dp),
        )
        Text(
            text = field,
            style =
                MaterialTheme.typography.subtitle2.copy(
                    color = Color.White,
                    letterSpacing = 1.5.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W400,
                ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = TMDb_2_dp),
        )
    }
}
