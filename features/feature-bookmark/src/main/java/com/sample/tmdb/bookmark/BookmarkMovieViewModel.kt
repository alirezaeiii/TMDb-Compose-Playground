package com.sample.tmdb.bookmark

import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.repository.BaseBookmarkRepository
import com.sample.tmdb.domain.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkMovieViewModel @Inject constructor(
    repository: BaseBookmarkRepository<Movie>,
    languageRepository: LanguageRepository,
) : BaseBookmarkViewModel<Movie>(repository, languageRepository)
