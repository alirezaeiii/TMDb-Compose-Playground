package com.sample.tmdb.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.tmdb.domain.model.DetailWrapper
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.SpokenLanguage
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.model.TVShowDetails
import com.sample.tmdb.domain.model.TrendingPerson

@Entity(tableName = "TVShowDetail")
class TVShowDetailEntity(
    @PrimaryKey val id: Int,
    val backdropPath: String?,
    val genres: List<Genre>,
    val homepage: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val cast: List<TrendingPerson>,
    val crew: List<TrendingPerson>,
    val images: List<TMDbImage>,
    val similarItems: List<TVShow>,
)

fun TVShowDetailEntity.asDomainModel(): DetailWrapper = DetailWrapper(
    cast = cast,
    crew = crew,
    details = TVShowDetails(
        backdropPath = backdropPath,
        genres = genres,
        homepage = homepage,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        spokenLanguages = spokenLanguages,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
    ),
    images = images,
    similarItems = similarItems,
)

fun DetailWrapper.asTVShowDetailDatabaseModel(id: Int): TVShowDetailEntity = TVShowDetailEntity(
    id = id,
    backdropPath = details.backdropPath,
    genres = details.genres,
    homepage = details.homepage,
    originalLanguage = details.originalLanguage,
    originalTitle = details.originalTitle,
    overview = details.overview,
    popularity = details.popularity,
    posterPath = details.posterPath,
    releaseDate = details.releaseDate,
    spokenLanguages = details.spokenLanguages,
    status = details.status,
    tagline = details.tagline,
    title = details.title,
    voteAverage = details.voteAverage,
    voteCount = details.voteCount,
    cast = cast,
    crew = crew,
    images = images,
    similarItems = similarItems.filterIsInstance<TVShow>(),
)
