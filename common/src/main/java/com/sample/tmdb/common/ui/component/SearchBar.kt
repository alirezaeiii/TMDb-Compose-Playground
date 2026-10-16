package com.sample.tmdb.common.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.R as commonR
import com.sample.tmdb.common.ui.Dimens.TMDb_24_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp

@Composable
fun SearchBar(
    query: String,
    @StringRes resourceId: Int,
    onQueryChange: (String) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        contentColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small,
        modifier =
            modifier
                .fillMaxWidth()
                .height(46.dp)
                .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                .background(MaterialTheme.colors.background, CircleShape)
                .padding(horizontal = TMDb_24_dp, vertical = TMDb_8_dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {
            if (query.isEmpty()) {
                SearchHint(resourceId)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .wrapContentHeight(),
            ) {
                BasicTextField(
                    value = query,
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onBackground),
                    cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
                    onValueChange = onQueryChange,
                    modifier =
                        Modifier
                            .weight(1f)
                            .onFocusChanged {
                                onSearchFocusChange(it.isFocused)
                            },
                )
                if (searchFocused && query.isNotEmpty()) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = stringResource(id = commonR.string.clear),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchHint(@StringRes resourceId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxSize()
                .wrapContentSize(),
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(id = commonR.string.search_desc),
        )
        Spacer(Modifier.width(TMDb_8_dp))
        Text(
            text = stringResource(commonR.string.search, stringResource(resourceId)),
            color = MaterialTheme.colors.primary,
        )
    }
}
