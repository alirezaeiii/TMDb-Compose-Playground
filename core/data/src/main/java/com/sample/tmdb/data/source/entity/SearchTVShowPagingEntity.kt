package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import com.sample.tmdb.domain.model.TVShow

@Entity(
    tableName = "SearchTVShowPaging",
    primaryKeys = ["queryStr", "id"],
)
data class SearchTVShowPagingEntity(
    val queryStr: String,
    val id: Int,
    val overview: String,
    val releaseDate: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int,
    val itemOrder: Int,
)

fun List<SearchTVShowPagingEntity>.asDomainModel() = map(SearchTVShowPagingEntity::asDomainModel)

fun SearchTVShowPagingEntity.asDomainModel(): TVShow = TVShow(
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    name = name,
    voteAverage = voteAverage,
    voteCount = voteCount,
)
