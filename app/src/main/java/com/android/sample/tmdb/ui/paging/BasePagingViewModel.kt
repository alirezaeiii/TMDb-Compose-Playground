package com.android.sample.tmdb.ui.paging

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.android.sample.tmdb.domain.model.TMDbItem
import kotlinx.coroutines.flow.Flow

abstract class BasePagingViewModel<T : TMDbItem> : ViewModel() {

    abstract val pagingDataFlow: Flow<PagingData<T>>
}