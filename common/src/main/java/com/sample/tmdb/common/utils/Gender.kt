package com.sample.tmdb.common.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon
    get() =
        when (this) {
            Gender.MALE -> Icons.Rounded.Man
            Gender.FEMALE -> Icons.Rounded.Woman
        }

fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE
