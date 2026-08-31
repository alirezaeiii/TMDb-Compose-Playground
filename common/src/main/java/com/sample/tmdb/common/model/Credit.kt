package com.sample.tmdb.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman

interface Credit {
    val id: Any
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon
    get() =
        when (this) {
            Gender.MALE -> Icons.Rounded.Man
            Gender.FEMALE -> Icons.Rounded.Woman
        }
