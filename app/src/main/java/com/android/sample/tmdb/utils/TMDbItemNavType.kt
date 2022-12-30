package com.android.sample.tmdb.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.android.sample.tmdb.domain.model.TMDbItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TMDbItemNavType : NavType<TMDbItem>(isNullableAllowed = false) {

    private val gsonForTMDbItem: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(TMDbItem::class.java, TMDbItemTypeAdapter())
            .create()
    }

    override fun get(bundle: Bundle, key: String): TMDbItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): TMDbItem {
        return gsonForTMDbItem.fromJson(value, TMDbItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: TMDbItem) {
        bundle.putParcelable(key, value)
    }
}