package com.android.sample.tmdb.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.android.sample.tmdb.domain.model.Movie
import com.google.gson.Gson

class TMDbItemNavType : NavType<Movie>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Movie? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Movie {
        return Gson().fromJson(value, Movie::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Movie) {
        bundle.putParcelable(key, value)
    }
}