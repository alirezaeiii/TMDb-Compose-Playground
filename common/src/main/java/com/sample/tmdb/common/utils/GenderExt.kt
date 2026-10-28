package com.sample.tmdb.common.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman
import com.sample.tmdb.domain.model.Gender

val Gender.placeholderIcon
    get() =
        when (this) {
            Gender.MALE -> Icons.Rounded.Man
            Gender.FEMALE -> Icons.Rounded.Woman
        }
