package com.sample.tmdb.common.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.domain.model.Genre

@Composable
fun GenreChipsRow(
    genres: List<Genre>,
    selectedGenreIds: Set<Int>,
    onGenreSelected: (Genre) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Dimens.TMDb_16_dp),
        horizontalArrangement = Arrangement.spacedBy(Dimens.TMDb_8_dp),
    ) {
        items(genres, key = { it.id }) { genre ->
            val isSelected = selectedGenreIds.contains(genre.id)
            FilterChip(
                selected = isSelected,
                onClick = { onGenreSelected(genre) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                    selectedLabelColor = Color.White,
                ),
                label = {
                    Text(
                        text = genre.name.orEmpty(),
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.White else MaterialTheme.colors.onSurface,
                        ),
                    )
                },
            )
        }
    }
}
