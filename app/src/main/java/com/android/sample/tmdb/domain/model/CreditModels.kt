package com.android.sample.tmdb.domain.model

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman
import kotlinx.parcelize.Parcelize

interface Credit : Parcelable {
    val id: Any
    val credit: String
    val name: String
    val profilePath: String?
    val gender: Gender
}

@Parcelize
class Cast(
    override val credit: String,
    override val name: String,
    override val profilePath: String?,
    override val gender: Gender,
    override val id: Int
) : Credit

@Parcelize
class Crew(
    override val credit: String,
    override val name: String,
    override val profilePath: String?,
    override val gender: Gender,
    override val id: String
) : Credit

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon
    get() = when (this) {
        Gender.MALE -> Icons.Rounded.Man
        Gender.FEMALE -> Icons.Rounded.Woman
    }