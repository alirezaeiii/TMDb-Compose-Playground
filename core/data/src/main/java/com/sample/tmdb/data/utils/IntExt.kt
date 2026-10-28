package com.sample.tmdb.data.utils

import com.sample.tmdb.domain.model.Gender

fun Int.toGender() = if (this == 1) Gender.FEMALE else Gender.MALE
