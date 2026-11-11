package com.sample.tmdb.data.source.entity

import androidx.room.Entity

@Entity(
    tableName = "SearchTVShowRemoteKeys",
    primaryKeys = ["queryStr"],
)
data class SearchTVShowRemoteKeyEntity(val queryStr: String, val prevKey: Int?, val nextKey: Int?)
