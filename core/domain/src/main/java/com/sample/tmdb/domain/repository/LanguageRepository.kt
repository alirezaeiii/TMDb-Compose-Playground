package com.sample.tmdb.domain.repository

import com.sample.tmdb.domain.model.LanguageMode
import kotlinx.coroutines.flow.StateFlow

interface LanguageRepository {
    val languageMode: StateFlow<LanguageMode>
    val languageCode: StateFlow<String>
    fun setLanguageMode(mode: LanguageMode)
}
