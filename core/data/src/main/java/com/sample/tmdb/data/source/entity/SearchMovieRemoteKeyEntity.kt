package com.sample.tmdb.data.source.entity

import androidx.room.Entity

@Entity(
    tableName = "SearchMovieRemoteKeys",
    primaryKeys = ["queryStr"],
)
data class SearchMovieRemoteKeyEntity(val queryStr: String, val prevKey: Int?, val nextKey: Int?)
