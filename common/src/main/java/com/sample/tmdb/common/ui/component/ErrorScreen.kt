package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sample.tmdb.common.R
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp

@Composable
fun ErrorScreen(message: String, modifier: Modifier = Modifier, refresh: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style =
            MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onSurface,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
            ),
        )
        Spacer(Modifier.height(TMDb_16_dp))
        Button(onClick = refresh) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
