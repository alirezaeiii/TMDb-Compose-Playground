package com.sample.tmdb.common.ui

import androidx.lifecycle.ViewModel
import com.sample.tmdb.common.model.ThemeMode
import com.sample.tmdb.common.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ThemeViewModel @Inject constructor(private val themeRepository: ThemeRepository) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = themeRepository.themeMode

    val supportedThemes = ThemeMode.entries

    fun setThemeMode(mode: ThemeMode) {
        themeRepository.setThemeMode(mode)
    }
}
