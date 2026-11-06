package com.sample.tmdb.data.source.local

import androidx.room.TypeConverter
import com.sample.tmdb.domain.model.Gender
import com.sample.tmdb.domain.model.SortType

class FeedTypeConverters {
    @TypeConverter
    fun fromSortType(sortType: SortType): String = sortType.name

    @TypeConverter
    fun toSortType(value: String): SortType = SortType.valueOf(value)

    @TypeConverter
    fun fromGender(gender: Gender): String = gender.name

    @TypeConverter
    fun toGender(value: String): Gender = Gender.valueOf(value)
}
