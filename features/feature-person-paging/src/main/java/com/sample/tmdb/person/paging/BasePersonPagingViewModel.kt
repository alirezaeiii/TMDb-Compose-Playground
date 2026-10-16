package com.sample.tmdb.person.paging

import com.sample.tmdb.common.base.BasePagingViewModel
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.ui.PagingUiEvent
import com.sample.tmdb.navigation.TMDbNavKey

abstract class BasePersonPagingViewModel : BasePagingViewModel<TrendingPerson>() {

    override fun onItemClick(item: TrendingPerson) {
        emitEvent(PagingUiEvent.Navigate(TMDbNavKey.Person(item.id)))
    }
}
