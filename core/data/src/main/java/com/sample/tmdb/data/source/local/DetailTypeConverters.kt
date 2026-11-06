package com.sample.tmdb.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SpokenLanguage
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TrendingPerson

class DetailTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(list: List<Genre>?): String = gson.toJson(list)

    @TypeConverter
    fun toGenreList(json: String?): List<Genre> =
        json?.let { gson.fromJson(it, object : TypeToken<List<Genre>>() {}.type) } ?: emptyList()

    @TypeConverter
    fun fromSpokenLanguageList(list: List<SpokenLanguage>?): String = gson.toJson(list)

    @TypeConverter
    fun toSpokenLanguageList(json: String?): List<SpokenLanguage> =
        json?.let { gson.fromJson(it, object : TypeToken<List<SpokenLanguage>>() {}.type) } ?: emptyList()

    @TypeConverter
    fun fromTrendingPersonList(list: List<TrendingPerson>?): String = gson.toJson(list)

    @TypeConverter
    fun toTrendingPersonList(json: String?): List<TrendingPerson> =
        json?.let { gson.fromJson(it, object : TypeToken<List<TrendingPerson>>() {}.type) } ?: emptyList()

    @TypeConverter
    fun fromTMDbImageList(list: List<TMDbImage>?): String = gson.toJson(list)

    @TypeConverter
    fun toTMDbImageList(json: String?): List<TMDbImage> =
        json?.let { gson.fromJson(it, object : TypeToken<List<TMDbImage>>() {}.type) } ?: emptyList()

    @TypeConverter
    fun fromMovieList(list: List<Movie>?): String = gson.toJson(list)

    @TypeConverter
    fun toMovieList(json: String?): List<Movie> =
        json?.let { gson.fromJson(it, object : TypeToken<List<Movie>>() {}.type) } ?: emptyList()

    @TypeConverter
    fun fromTVShowList(list: List<TVShow>?): String = gson.toJson(list)

    @TypeConverter
    fun toTVShowList(json: String?): List<TVShow> =
        json?.let { gson.fromJson(it, object : TypeToken<List<TVShow>>() {}.type) } ?: emptyList()
}
