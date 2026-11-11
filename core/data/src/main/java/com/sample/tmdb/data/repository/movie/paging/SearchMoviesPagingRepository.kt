package com.sample.tmdb.data.repository.movie.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.sample.tmdb.data.network.MovieService
import com.sample.tmdb.data.paging.movie.SearchMovieRemoteMediator
import com.sample.tmdb.data.source.entity.SearchMoviePagingEntity
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.TMDbDatabase
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.repository.BasePagingRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMoviesPagingRepository @Inject constructor(
    private val database: TMDbDatabase,
    private val movieApi: MovieService,
) : BasePagingRepositoryImpl<Movie, SearchMoviePagingEntity>() {

    @OptIn(ExperimentalPagingApi::class)
    override fun createRemoteMediator(
        query: String?,
        id: Int?,
        type: SortType?,
    ): RemoteMediator<Int, SearchMoviePagingEntity> = SearchMovieRemoteMediator(database, movieApi, query!!)

    override fun createPagingSource(
        query: String?,
        id: Int?,
        type: SortType?,
    ): PagingSource<Int, SearchMoviePagingEntity> = database.searchMoviesPagingDao.getPagingMovies(query!!)

    override fun mapEntityToDomain(entity: SearchMoviePagingEntity): Movie = entity.asDomainModel()
}
