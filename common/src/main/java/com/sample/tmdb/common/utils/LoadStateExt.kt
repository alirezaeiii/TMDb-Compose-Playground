package com.sample.tmdb.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import com.sample.tmdb.domain.R

@Composable
fun getPagingErrorMsg(loadState: LoadState): String =
    (loadState as? LoadState.Error)?.error?.message ?: stringResource(R.string.failed_loading_msg)
