package com.sample.tmdb.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.R

object Dimens {

    val PaddingMicro: Dp
        @Composable get() = dimensionResource(R.dimen.padding_micro)

    val PaddingExtraSmall: Dp
        @Composable get() = dimensionResource(R.dimen.padding_extra_small)

    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.padding_small)

    val PaddingMedium: Dp
        @Composable get() = dimensionResource(R.dimen.padding_medium)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.padding_normal)

    val PaddingLarge: Dp
        @Composable get() = dimensionResource(R.dimen.padding_large)

    val PaddingExtraLarge: Dp
        @Composable get() = dimensionResource(R.dimen.padding_extra_large)

    val CreditCardSize = 120.dp
}