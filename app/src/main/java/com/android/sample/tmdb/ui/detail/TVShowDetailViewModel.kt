package com.android.sample.tmdb.ui.detail

import com.android.sample.tmdb.domain.model.DetailWrapper
import com.android.sample.tmdb.repository.TVShowDetailRepository
import com.android.sample.tmdb.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailViewModel@Inject constructor(repository: TVShowDetailRepository):
    BaseViewModel<DetailWrapper>(repository)