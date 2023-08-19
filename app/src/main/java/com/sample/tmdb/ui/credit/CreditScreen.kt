package com.sample.tmdb.ui.credit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.tmdb.domain.model.Credit
import com.sample.tmdb.ui.common.DestinationBar
import com.sample.tmdb.ui.common.Dimens
import com.sample.tmdb.ui.common.Person
import com.sample.tmdb.utils.toDp

@Composable
fun <T : Credit> CreditScreen(
    @StringRes resourceId: Int,
    upPress: () -> Unit,
    items: List<T>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimens.CreditCardSize),
        contentPadding = PaddingValues(
            start = Dimens.PaddingSmall,
            end = Dimens.PaddingSmall,
            top = Dimens.PaddingSmall,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.GridSpacing
                )
        ),
        content = {

            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
            }

            items(items.size) { index ->
                Person(person = items[index])
            }
        }
    )
    DestinationBar(title = stringResource(resourceId), upPress = upPress)
}