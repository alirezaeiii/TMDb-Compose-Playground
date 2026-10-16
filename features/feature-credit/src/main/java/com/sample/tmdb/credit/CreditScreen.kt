package com.sample.tmdb.credit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.common.model.TYPE
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_120_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.utils.TMDbSpacer
import com.sample.tmdb.common.utils.fullSpanGridItem
import com.sample.tmdb.common.utils.navigationBarPadding
import com.sample.tmdb.navigation.TMDbNavKey

sealed interface CreditSource {
    data class FromViewModel(val viewModel: PeopleViewModel, val onNavigate: (TMDbNavKey) -> Unit) : CreditSource

    data class FromJson(
        val json: String,
        val upPress: () -> Unit,
        val onPersonClicked: (person: TrendingPerson) -> Unit,
    ) : CreditSource
}

@Composable
fun CreditScreen(source: CreditSource, @StringRes resourceId: Int, testPainter: Painter? = null) {
    when (source) {
        is CreditSource.FromViewModel -> {
            Content(
                viewModel = source.viewModel,
                onNavigate = source.onNavigate,
            ) { _, item ->
                CreditScreen(
                    resourceId = resourceId,
                    items = item.result,
                    onPersonClicked = source.viewModel::onPersonClick,
                    type = item.type,
                    testPainter = testPainter,
                )
            }
        }

        is CreditSource.FromJson -> {
            val items = remember(source.json) {
                val gson = Gson()
                gson.fromJson<List<TrendingPerson>>(source.json, object : TypeToken<List<TrendingPerson>>() {}.type)
            }
            CreditScreen(
                resourceId = resourceId,
                upPress = source.upPress,
                onPersonClicked = source.onPersonClicked,
                items = items,
                type = TYPE.CREDIT,
                testPainter = testPainter,
            )
        }
    }
}

@Composable
fun CreditScreen(
    @StringRes resourceId: Int,
    upPress: () -> Unit = {},
    onPersonClicked: (person: TrendingPerson) -> Unit,
    items: List<TrendingPerson>,
    type: TYPE,
    testPainter: Painter? = null,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(TMDb_120_dp),
        contentPadding =
            PaddingValues(
                start = TMDb_6_dp,
                end = TMDb_6_dp,
                top = TMDb_6_dp,
                bottom = navigationBarPadding().plus(TMDb_8_dp),
            ),
        content = {
            fullSpanGridItem {
                TMDbSpacer()
            }

            items(items.size) { index ->
                PersonCard(
                    person = items[index],
                    onPersonClicked = onPersonClicked,
                    testPainter = testPainter,
                )
            }
            if (type == TYPE.TRENDING) {
                fullSpanGridItem {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = TMDb_8_dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularLoadMoreButton { }
                    }
                    Spacer(Modifier.height(navigationBarPadding() + TMDb_120_dp))
                }
            }
        },
    )
    DestinationBar(title = stringResource(resourceId), upPress = upPress::invoke)
}

@Composable
fun CircularLoadMoreButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .width(186.dp)
            .height(48.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Text(stringResource(R.string.more))
    }
}
