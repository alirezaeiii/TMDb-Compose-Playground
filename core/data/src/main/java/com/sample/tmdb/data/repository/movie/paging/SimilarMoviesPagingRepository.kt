package com.sample.tmdb.data.repository.movie.paging

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.movie.SimilarMovieRemoteMediator
import com.sample.tmdb.data.repository.BasePagingRepository
import com.sample.tmdb.data.source.entity.SimilarMoviePagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimilarMoviesPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val movieApi: MovieService,
    @ApplicationContext private val context: Context,
) : BasePagingRepository<Movie, SimilarMoviePagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, SimilarMoviePagingEntity> = SimilarMovieRemoteMediator(database, movieApi, id!!, context)

    override fun createPagingSource(
        query: String?,
        id: Int?,
        type: SortType?,
    ): PagingSource<Int, SimilarMoviePagingEntity> = database.similarMoviePagingDao.getPagingMovies(id!!)

    override fun mapEntityToDomain(entity: SimilarMoviePagingEntity): Movie = entity.asDomainModel()
}
