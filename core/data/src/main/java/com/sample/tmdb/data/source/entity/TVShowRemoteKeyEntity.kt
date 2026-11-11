package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.SortType

@Entity(
    tableName = "TVShowRemoteKeys",
    primaryKeys = ["sortType"],
)
class TVShowRemoteKeyEntity(val sortType: SortType, val prevKey: Int?, val nextKey: Int?)
