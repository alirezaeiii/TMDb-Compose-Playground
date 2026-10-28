package com.sample.tmdb.bookmark

import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkTVShowViewModel @Inject constructor(
    repository: BaseBookmarkRepository<TVShow>,
    languageRepository: LanguageRepository,
) : BaseBookmarkViewModel<TVShow>(repository, languageRepository)
