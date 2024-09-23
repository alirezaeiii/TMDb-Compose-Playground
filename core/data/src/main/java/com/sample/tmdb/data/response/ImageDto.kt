package com.sample.tmdb.data.response

import com.sample.tmdb.common.utils.Constants.BASE_IMAGE_PATH
import com.sample.tmdb.common.utils.Constants.ID
import com.sample.tmdb.common.utils.Constants.VOTE_AVERAGE
import com.sample.tmdb.common.utils.Constants.VOTE_COUNT
import com.sample.tmdb.domain.model.TMDbImage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesResponse(
    @Json(name = BACKDROPS) val backdrops: List<ImageResponse>,
    @Json(name = ID) val id: Int,
    @Json(name = POSTERS) val posters: List<ImageResponse>,
)

@JsonClass(generateAdapter = true)
data class ImageResponse(
    @Json(name = ASPECT_RATIO) val aspectRatio: Double,
    @Json(name = FILE_PATH) val filePath: String,
    @Json(name = HEIGHT) val height: Int,
    @Json(name = ISO_639_1) val iso6391: String?,
    @Json(name = VOTE_AVERAGE) val voteAverage: Double,
    @Json(name = VOTE_COUNT) val voteCount: Int,
    @Json(name = WIDTH) val width: Int,
)

fun ImagesResponse.asDomainModel(): List<TMDbImage> = buildList {
    addAll(backdrops.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    addAll(posters.map { TMDbImage(String.format(BASE_IMAGE_PATH, it.filePath), it.voteCount) })
    sortByDescending { it.voteCount }
}

private const val BACKDROPS = "backdrops"
private const val POSTERS = "posters"
private const val ASPECT_RATIO = "aspect_ratio"
private const val FILE_PATH = "file_path"
private const val HEIGHT = "height"
private const val ISO_639_1 = "iso_639_1"
private const val WIDTH = "width"
