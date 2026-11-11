package com.sample.tmdb.data.source.entity

import androidx.room.Entity

@Entity(tableName = "SimilarTVShowRemoteKeys", primaryKeys = ["id"])
class SimilarTVShowRemoteKeyEntity(val id: Int, val prevKey: Int?, val nextKey: Int?)
