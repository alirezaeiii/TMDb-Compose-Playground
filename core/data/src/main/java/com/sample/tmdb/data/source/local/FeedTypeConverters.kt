package com.sample.tmdb.data.source.local

import androidx.room.TypeConverter
import com.sample.tmdb.domain.model.SortType

class FeedTypeConverters {
    @TypeConverter
    fun fromSortType(sortType: SortType): String = sortType.name

    @TypeConverter
    fun toSortType(value: String): SortType = SortType.valueOf(value)
}
