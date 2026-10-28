package com.sample.tmdb.data.repository

import android.app.Application
import android.app.LocaleManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.sample.tmdb.domain.model.LanguageMode
import com.sample.tmdb.domain.repository.LanguageRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Singleton
class LanguageRepositoryImpl @Inject constructor(private val application: Application) : LanguageRepository {

    private val sharedPreferences = application.getSharedPreferences("tmdb_prefs", Context.MODE_PRIVATE)

    private val initialMode = LanguageMode.valueOf(
        sharedPreferences.getString(KEY_LANGUAGE_MODE, LanguageMode.SYSTEM.name) ?: LanguageMode.SYSTEM.name,
    )

    private val _languageMode = MutableStateFlow(initialMode)
    override val languageMode: StateFlow<LanguageMode> = _languageMode.asStateFlow()

    private val _languageCode = MutableStateFlow(resolveLanguageCode(initialMode))
    override val languageCode: StateFlow<String> = _languageCode.asStateFlow()

    init {
        applyLanguageMode(initialMode)
    }

    override fun setLanguageMode(mode: LanguageMode) {
        sharedPreferences.edit()
            .putString(KEY_LANGUAGE_MODE, mode.name)
            .apply()

        applyLanguageMode(mode)

        _languageMode.value = mode
        _languageCode.value = resolveLanguageCode(mode)
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
        LanguageMode.SYSTEM -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val localeManager =
                    application.getSystemService(LocaleManager::class.java)

                localeManager.systemLocales[0]?.language ?: "en"
            } else {
                ConfigurationCompat.getLocales(
                    Resources.getSystem().configuration,
                )[0]?.language ?: "en"
            }
        }

        LanguageMode.ENGLISH -> LanguageMode.ENGLISH.code

        LanguageMode.SPANISH -> LanguageMode.SPANISH.code
    }

    companion object {
        private const val KEY_LANGUAGE_MODE = "language_mode"
    }
}
