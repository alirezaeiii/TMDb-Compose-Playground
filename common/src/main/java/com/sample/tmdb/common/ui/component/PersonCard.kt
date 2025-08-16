package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.placeholderIcon
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_2_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_4_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.theme.imageTint
import com.sample.tmdb.common.utils.CircleTopCropTransformation

@Composable
fun PersonCard(
    person: Credit,
    navController: NavController,
    modifier: Modifier = Modifier,
    testPainter: Painter? = null,
) {
    Column(
        modifier
            .padding(TMDb_4_dp)
            .clickable {
                navController.navigate("${MainDestinations.TMDB_PERSON_ROUTE}/${person.id}")
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = CircleShape,
            elevation = TMDb_8_dp,
            modifier = Modifier.size(TMDb_120_dp),
        ) {
            val request =
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(person.profileUrl)
                    .crossfade(true)
                    .transformations(CircleTopCropTransformation())
                    .build()
            val placeholderPainter = rememberVectorPainter(person.gender.placeholderIcon)
            val painter = testPainter ?: rememberAsyncImagePainter(
                model = request,
                error = placeholderPainter,
                placeholder = placeholderPainter,
            )
            val colorFilter: ColorFilter? = if (painter is AsyncImagePainter) {
                when (painter.state) {
                    is AsyncImagePainter.State.Error,
                    is AsyncImagePainter.State.Loading,
                        -> ColorFilter.tint(MaterialTheme.colors.imageTint)

                    else -> null
                }
            } else {
                // For testPainter or any stable Painter
                ColorFilter.tint(MaterialTheme.colors.imageTint)
            }
            Image(
                painter = painter,
                colorFilter = colorFilter,
                contentDescription =
                stringResource(
                    id = R.string.person_content_description,
                    person.name,
                    person.role,
                ),
                contentScale = ContentScale.FillHeight,
            )
        }
        Text(
            text = person.name,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = TMDb_4_dp),
        )
        Text(
            text = person.role,
            style =
            MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = TMDb_2_dp),
        )
    }
}
