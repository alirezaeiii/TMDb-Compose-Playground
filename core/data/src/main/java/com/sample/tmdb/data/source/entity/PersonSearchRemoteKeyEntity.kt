package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonSearchRemoteKeys")
class PersonSearchRemoteKeyEntity(@PrimaryKey val queryStr: String, val prevKey: Int?, val nextKey: Int?)
