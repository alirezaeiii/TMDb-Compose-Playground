package com.sample.tmdb.domain.model

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Man
import androidx.compose.material.icons.rounded.Woman
import kotlinx.parcelize.Parcelize

interface Credit : Parcelable {
    val id: Any
    val role: String
    val name: String
    val profileUrl: String?
    val gender: Gender
}

@Parcelize
class Cast(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: Int
) : Credit

@Parcelize
class Crew(
    override val role: String,
    override val name: String,
    override val profileUrl: String?,
    override val gender: Gender,
    override val id: String
) : Credit

enum class Gender { MALE, FEMALE }

val Gender.placeholderIcon
    get() = when (this) {
        Gender.MALE -> Icons.Rounded.Man
        Gender.FEMALE -> Icons.Rounded.Woman
    }