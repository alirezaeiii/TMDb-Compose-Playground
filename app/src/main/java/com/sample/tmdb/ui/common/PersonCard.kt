package com.sample.tmdb.ui.common

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sample.tmdb.R
import com.sample.tmdb.domain.model.Credit
import com.sample.tmdb.domain.model.placeholderIcon
import com.sample.tmdb.ui.theme.imageTint
import com.sample.tmdb.utils.CircleTopCropTransformation

@Composable
fun Person(person: Credit, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            elevation = 8.dp,
            modifier = Modifier.size(Dimens.CreditCardSize)
        ) {
            val request = ImageRequest.Builder(LocalContext.current)
                .data(person.profileUrl)
                .crossfade(true)
                .transformations(CircleTopCropTransformation())
                .build()
            val placeholderPainter = rememberVectorPainter(person.gender.placeholderIcon)
            val painter =
                rememberAsyncImagePainter(
                    model = request,
                    error = placeholderPainter,
                    placeholder = placeholderPainter
                )
            val colorFilter = when (painter.state) {
                is AsyncImagePainter.State.Error, is AsyncImagePainter.State.Loading -> ColorFilter.tint(
                    MaterialTheme.colors.imageTint
                )
                else -> null
            }
            Image(
                painter = painter,
                colorFilter = colorFilter,
                contentDescription = stringResource(
                    id = R.string.person_content_description,
                    person.name,
                    person.role
                ),
                contentScale = ContentScale.FillHeight
            )
        }
        Text(
            text = person.name,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = person.role,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}