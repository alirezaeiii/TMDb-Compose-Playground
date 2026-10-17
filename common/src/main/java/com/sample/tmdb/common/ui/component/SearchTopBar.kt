package com.sample.tmdb.common.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.ui.Dimens.TMDb_12_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_16_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_32_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.theme.AlphaNearOpaque

@Composable
fun SearchTopBar(
    query: String,
    @StringRes resourceId: Int,
    onQueryChange: (String) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .statusBarsPadding()
                .background(MaterialTheme.colors.background.copy(alpha = AlphaNearOpaque)),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(TMDb_8_dp),
            horizontalArrangement =
                Arrangement.spacedBy(
                    TMDb_16_dp,
                    Alignment.CenterHorizontally,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val iconModifier =
                Modifier
                    .sizeIn(
                        maxWidth = TMDb_32_dp,
                        maxHeight = TMDb_32_dp,
                    )
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = CircleShape,
                    )
            IconButton(
                onClick = onNavigateUp,
                modifier =
                    Modifier
                        .padding(start = TMDb_12_dp)
                        .then(iconModifier),
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = commonR.string.back),
                    tint = MaterialTheme.colors.primary,
                )
            }
            SearchBar(
                query = query,
                resourceId = resourceId,
                onQueryChange = onQueryChange,
                searchFocused = searchFocused,
                onSearchFocusChange = onSearchFocusChange,
                onClearQuery = onClearQuery,
            )
        }
        TMDbDivider()
    }
}
