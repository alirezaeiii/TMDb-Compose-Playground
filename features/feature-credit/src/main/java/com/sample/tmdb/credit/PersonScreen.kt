package com.sample.tmdb.credit

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sample.tmdb.common.R as R1
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_4_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.component.TMDbDivider
import com.sample.tmdb.common.ui.theme.Neutral8
import com.sample.tmdb.common.ui.theme.Tornado
import com.sample.tmdb.common.utils.CircleTopCropTransformation
import com.sample.tmdb.domain.model.Person
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 36.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MediumTitleOffset = ImageOverlap + MinTitleOffset
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun PersonScreen(upPress: () -> Unit, viewModel: PersonViewModel = hiltViewModel()) {
    Content(viewModel = viewModel) {
        PersonScreen(it, upPress)
    }
}

@Composable
fun PersonScreen(person: Person, upPress: () -> Unit) {
    val titleHeight = remember { mutableStateOf(0.dp) }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val scroll = rememberScrollState(0)
        Header()
        Body(person.biography, titleHeight, scroll)
        Title(person, titleHeight) { scroll.value }
        person.profilePath?.let {
            Image(it) { scroll.value }
        }
        Up(upPress)
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier =
        Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(Tornado)),
    )
}

@Composable
fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier =
        Modifier
            .statusBarsPadding()
            .padding(horizontal = TMDb_16_dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape,
            ),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = stringResource(R1.string.back),
        )
    }
}

@Composable
fun Body(biography: String, titleHeight: MutableState<Dp>, scroll: ScrollState) {
    Column {
        Spacer(
            modifier =
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset),
        )
        Column(
            modifier = Modifier.verticalScroll(scroll),
        ) {
            Spacer(Modifier.height(GradientScroll))
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(96.dp))
                    Spacer(Modifier.height(titleHeight.value))

                    if (biography.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.biography),
                            style = MaterialTheme.typography.overline,
                            color = MaterialTheme.colors.onSurface,
                            modifier = HzPadding,
                        )
                        Spacer(Modifier.height(TMDb_16_dp))
                        Text(
                            text = biography,
                            style =
                            MaterialTheme.typography.body2.copy(
                                letterSpacing = 2.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.SansSerif,
                            ),
                            color = MaterialTheme.colors.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            modifier = HzPadding,
                        )
                    }
                    Spacer(
                        modifier =
                        Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding()
                            .height(TMDb_8_dp),
                    )
                }
            }
        }
    }
}

@Composable
fun Title(person: Person, titleHeight: MutableState<Dp>, scrollProvider: () -> Int) {
    val localDestiny = LocalDensity.current
    val maxOffset =
        with(localDestiny) {
            (if (person.profilePath == null) MediumTitleOffset else MaxTitleOffset).toPx()
        }
    val minOffset = with(localDestiny) { MinTitleOffset.toPx() }
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier =
        Modifier
            .onGloballyPositioned { coordinates ->
                with(localDestiny) { titleHeight.value = coordinates.size.height.toDp() }
            }.heightIn(min = titleHeight.value)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }.background(color = MaterialTheme.colors.surface),
    ) {
        Spacer(Modifier.height(TMDb_16_dp))
        Text(
            text = person.name,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface,
            modifier = HzPadding,
        )
        Spacer(Modifier.height(TMDb_2_dp))
        person.placeOfBirth?.let {
            Text(
                text = stringResource(id = R.string.from, it),
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colors.onSurface,
                modifier = HzPadding,
            )
            Spacer(Modifier.height(TMDb_4_dp))
        }
        person.birthDay?.let {
            Text(
                text = stringResource(id = R.string.born, it),
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colors.onSurface,
                modifier = HzPadding,
            )
            Spacer(Modifier.height(TMDb_4_dp))
        }
        person.deathDay?.let {
            Text(
                text = stringResource(id = R.string.death, it),
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colors.onSurface,
                modifier = HzPadding,
            )
            Spacer(Modifier.height(TMDb_4_dp))
        }
        Spacer(Modifier.height(TMDb_12_dp))
        TMDbDivider()
    }
}

@Composable
private fun Image(imageUrl: String, scrollProvider: () -> Int) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding()),
    ) {
        val request =
            ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .transformations(CircleTopCropTransformation())
                .build()
        val painter = rememberAsyncImagePainter(model = request)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth).toDp()
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth).toDp()
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction).roundToPx()
        val imagePlaceable =
            measurables[0].measure(
                Constraints.fixed(
                    imageWidth,
                    imageWidth,
                ),
            )

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX =
            lerp(
                ((constraints.maxWidth - imageWidth) / 2).toDp(), // centered when expanded
                (constraints.maxWidth - imageWidth).toDp(), // right aligned when collapsed
                collapseFraction,
            ).roundToPx()
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth,
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}
