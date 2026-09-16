package com.sample.tmdb.common.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.sample.tmdb.common.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) : ViewModel() {
    val languageCode: StateFlow<String> = languageRepository.languageCode

    val supportedLanguages = listOf("es", "en")

    fun setLanguage(code: String) {
        languageRepository.setLanguage(code)
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(code))
    }
}
