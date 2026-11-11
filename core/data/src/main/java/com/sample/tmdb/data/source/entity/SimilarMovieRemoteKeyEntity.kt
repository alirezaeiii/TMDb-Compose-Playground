package com.sample.tmdb.data.source.entity

import androidx.room.Entity

@Entity(tableName = "SimilarMovieRemoteKeys", primaryKeys = ["id"])
class SimilarMovieRemoteKeyEntity(val id: Int, val prevKey: Int?, val nextKey: Int?)
