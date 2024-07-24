package com.sample.tmdb.image

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_4_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.common.R as R1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesScreen(images: List<TMDbImage>, initialPage: Int) {
    if (images.isEmpty() || initialPage !in images.indices) return

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        initialPageOffsetFraction = 0f
    ) { images.size }
    Box {
        HorizontalPager(state = pagerState, key = { images[it].url + it }, beyondBoundsPageCount = 4) {
            Poster(images[it])
        }
        Index(position = pagerState.currentPage + 1, imageCount = pagerState.pageCount)
    }
}

@Composable
private fun Poster(image: TMDbImage) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        BlurImage(image.url)
        Card(
            Modifier
                .systemBarsPadding()
                .padding(TMDb_12_dp)
                .shadow(TMDb_16_dp, RoundedCornerShape(TMDb_12_dp))
                .animateContentSize()
                .wrapContentSize()
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(image.url),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.FillWidth,
                )
                VoteCount(image.voteCount)
            }
        }
    }
}

@Composable
private fun BlurImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = stringResource(id = R1.string.poster_content_description),
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .blur(TMDb_16_dp),
    )
}

@Composable
private fun BoxScope.VoteCount(voteCount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .align(Alignment.BottomStart)
            .background(
                color = MaterialTheme.colors.surface.copy(alpha = 0.3f),
                shape = RoundedCornerShape(bottomStart = TMDb_12_dp, topEnd = TMDb_12_dp),
            )
            .padding(TMDb_4_dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(id = R.string.likes_content_description),
            modifier = Modifier.padding(end = TMDb_4_dp),
        )
        Text(text = voteCount.toString(), style = MaterialTheme.typography.body2)
    }
}

@Composable
private fun BoxScope.Index(position: Int, imageCount: Int) {
    Text(
        text = "$position / $imageCount",
        style = MaterialTheme.typography.body2,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .navigationBarsPadding()
            .padding(TMDb_4_dp)
            .shadow(TMDb_16_dp, RoundedCornerShape(TMDb_16_dp))
            .background(color = MaterialTheme.colors.surface.copy(alpha = 0.3f))
            .padding(horizontal = TMDb_8_dp, vertical = TMDb_2_dp),
    )
}