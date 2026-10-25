package com.sample.tmdb.credit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens.TMDb_136_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_6_dp
import com.sample.tmdb.common.ui.Dimens.TMDb_8_dp
import com.sample.tmdb.common.ui.LanguageViewModel
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.ui.component.PersonGrid
import com.sample.tmdb.common.ui.component.TMDbSwipeRefresh
import com.sample.tmdb.common.utils.TMDbSpacer
import com.sample.tmdb.common.utils.fullSpanGridItem
import com.sample.tmdb.common.utils.navigationBarPadding
import com.sample.tmdb.navigation.TMDbNavKey
import com.sample.tmdb.navigation.model.TrendingPerson

sealed interface CreditSource {
    data class FromViewModel(
        val viewModel: PeopleViewModel,
        val languageViewModel: LanguageViewModel,
        val onNavigate: (TMDbNavKey) -> Unit,
    ) : CreditSource

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
                languageViewModel = source.languageViewModel,
                onNavigate = source.onNavigate,
            ) { state, item ->
                TMDbSwipeRefresh(state.isRefreshing, { source.viewModel.refresh(true) }) {
                    CreditScreen(
                        resourceId = resourceId,
                        items = item.result,
                        onSearchClick = source.viewModel::onSearchClick,
                        onPersonClicked = source.viewModel::onPersonClick,
                        type = item.type,
                        onMoreClick = source.viewModel::onMoreClick,
                        testPainter = testPainter,
                    )
                }
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
    upPress: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onPersonClicked: (person: TrendingPerson) -> Unit,
    items: List<TrendingPerson>,
    type: TYPE,
    onMoreClick: () -> Unit = {},
    testPainter: Painter? = null,
) {
    PersonGrid(
        contentPadding =
            PaddingValues(
                start = TMDb_6_dp,
                end = TMDb_6_dp,
                top = TMDb_6_dp,
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

            fullSpanGridItem {
                if (type == TYPE.TRENDING) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = TMDb_8_dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularLoadMoreButton(onMoreClick)
                    }
                    Spacer(Modifier.height(navigationBarPadding() + TMDb_136_dp))
                } else {
                    Spacer(Modifier.height(navigationBarPadding() + TMDb_8_dp))
                }
            }
        },
    )
    DestinationBar(
        title = stringResource(resourceId),
        onSearchClicked = onSearchClick,
        upPress = upPress,
    )
}

@Composable
fun CircularLoadMoreButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
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
