package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonRemoteKeys")
class PersonRemoteKeyEntity(@PrimaryKey val id: Int, val prevKey: Int?, val nextKey: Int?)
