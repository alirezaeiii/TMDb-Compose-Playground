package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants.BASE_IMAGE_PATH
import com.sample.tmdb.domain.model.TMDbImage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = "backdrops") val backdrops: List<ImageResponse>,
    @Json(name = "id") val id: Int,
    @Json(name = "posters") val posters: List<ImageResponse>,
)

@JsonClass(generateAdapter = true)
data class ImageResponse(
    @Json(name = "aspect_ratio") val aspectRatio: Double,
    @Json(name = "file_path") val filePath: String,
    @Json(name = "height") val height: Int,
    @Json(name = "iso_639_1") val iso6391: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "width") val width: Int,
)

fun ImagesResponse.asDomainModel(): List<TMDbImage> = buildList {
    addAll(backdrops.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    addAll(posters.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    sortByDescending { it.voteCount }
}