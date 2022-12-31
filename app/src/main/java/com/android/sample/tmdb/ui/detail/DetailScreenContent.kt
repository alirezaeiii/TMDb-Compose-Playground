package com.android.sample.tmdb.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.ui.common.ErrorScreen
import com.android.sample.tmdb.ui.common.TMDbProgressBar
import com.android.sample.tmdb.utils.Resource

@Composable
fun MyContent(
    viewModel: MovieDetailViewModel,
    SuccessScreen: @Composable (t: DetailWrapper) -> Unit
) {
    when (val resource = viewModel.stateFlow.collectAsState().value) {
        is Resource.Loading -> TMDbProgressBar()
        is Resource.Success -> SuccessScreen(resource.data)
        is Resource.Error -> ErrorScreen(message = resource.message, refresh = viewModel::refresh)
    }
}

@Composable
fun DetailScreenContent(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    MyContent(viewModel = viewModel) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = it.cast.toString(),
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailScreenContent(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Detail",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}