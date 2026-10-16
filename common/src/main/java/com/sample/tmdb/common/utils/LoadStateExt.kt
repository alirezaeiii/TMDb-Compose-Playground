package com.sample.tmdb.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import com.sample.tmdb.common.R as commonR

@Composable
fun getPagingErrorMsg(loadState: LoadState): String =
    (loadState as? LoadState.Error)?.error?.message ?: stringResource(commonR.string.failed_loading_msg)
