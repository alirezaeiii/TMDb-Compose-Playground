package com.sample.tmdb.common.repository

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import androidx.core.os.LocaleListCompat
import com.sample.tmdb.common.model.LanguageMode
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class LanguageRepository @Inject constructor(application: Application) {

    private val sharedPreferences = application.getSharedPreferences("tmdb_prefs", Context.MODE_PRIVATE)

    private val initialMode = LanguageMode.valueOf(
        sharedPreferences.getString(KEY_LANGUAGE_MODE, LanguageMode.SYSTEM.name) ?: LanguageMode.SYSTEM.name,
    )

    private val _languageMode = MutableStateFlow(initialMode)
    val languageMode: StateFlow<LanguageMode> = _languageMode.asStateFlow()

    private val _languageCode = MutableStateFlow(resolveLanguageCode(initialMode))
    val languageCode: StateFlow<String> = _languageCode.asStateFlow()

    init {
        applyLanguageMode(initialMode)
    }

    fun setLanguageMode(mode: LanguageMode) {
        sharedPreferences.edit().putString(KEY_LANGUAGE_MODE, mode.name).apply()
        _languageMode.value = mode
        _languageCode.value = resolveLanguageCode(mode)
        applyLanguageMode(mode)
    }

    private fun applyLanguageMode(mode: LanguageMode) {
        val localeList = when (mode) {
            LanguageMode.SYSTEM -> LocaleListCompat.getEmptyLocaleList()
            LanguageMode.ENGLISH -> LocaleListCompat.forLanguageTags(mode.code)
            LanguageMode.SPANISH -> LocaleListCompat.forLanguageTags(mode.code)
        }
        AppCompatDelegate.setApplicationLocales(localeList)
    }

    private fun resolveLanguageCode(mode: LanguageMode): String = when (mode) {
        LanguageMode.SYSTEM -> Resources.getSystem().configuration.locales[0]?.language
            ?: Locale.current.language

        LanguageMode.ENGLISH -> LanguageMode.ENGLISH.code

        LanguageMode.SPANISH -> LanguageMode.SPANISH.code
    }

    companion object {
        private const val KEY_LANGUAGE_MODE = "language_mode"
    }
}
