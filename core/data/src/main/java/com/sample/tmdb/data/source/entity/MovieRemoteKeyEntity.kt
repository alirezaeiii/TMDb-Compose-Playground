package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.SortType

@Entity(
    tableName = "MovieRemoteKeys",
    primaryKeys = ["sortType"],
)
class MovieRemoteKeyEntity(val sortType: SortType, val prevKey: Int?, val nextKey: Int?)
