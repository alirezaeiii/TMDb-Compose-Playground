package com.sample.tmdb.paging

import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.PagingUiEvent

abstract class BaseTMDbPagingViewModel<T : TMDbItem> : BasePagingViewModel<T>() {
    open fun onItemClick(item: TMDbItem) {
        emitEvent(PagingUiEvent.Navigate(item.detailNavKey))
    }
}
