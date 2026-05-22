package com.sample.tmdb.domain.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.common.model.TMDbItem
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseBookmarkRepository<T : TMDbItem>(context: Context, ioDispatcher: CoroutineDispatcher) :
    BaseRepository<List<T>, Nothing>(context, ioDispatcher)
