package com.sample.tmdb.common.ui

import androidx.lifecycle.ViewModel
import com.sample.tmdb.domain.model.LanguageMode
import com.sample.tmdb.domain.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) : ViewModel() {
    val languageMode: StateFlow<LanguageMode> = languageRepository.languageMode
    val languageCode: StateFlow<String> = languageRepository.languageCode

    val supportedLanguages = LanguageMode.entries

    fun setLanguage(mode: LanguageMode) {
        languageRepository.setLanguageMode(mode)
    }
}
