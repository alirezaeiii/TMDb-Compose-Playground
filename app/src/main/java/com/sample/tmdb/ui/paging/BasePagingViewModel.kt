package com.sample.tmdb.ui.paging

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.sample.tmdb.common.model.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingViewModel<T : TMDbItem> : ViewModel() {

    abstract val pagingDataFlow: Flow<PagingData<T>>
}