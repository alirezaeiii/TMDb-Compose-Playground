package com.sample.tmdb.data.repository.tvshow.detail

import android.content.Context
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asCastDomainModel
import com.sample.tmdb.data.response.asCrewDomainModel
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.TMDbImage
import com.sample.tmdb.domain.model.TvDetails
import com.sample.tmdb.domain.repository.BaseDetailRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class TVShowDetailRepository
@Inject
constructor(
    private val tvShowApi: TVShowService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TvDetails>(context, ioDispatcher) {
    override suspend fun getDetails(id: Int): TvDetails = tvShowApi.fetchTvDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = tvShowApi.tvCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel(),
        )
    }

    override suspend fun getImages(id: Int): List<TMDbImage> = tvShowApi.fetchImages(id).asDomainModel()

    override suspend fun getSimilarItems(id: Int): List<TMDbItem> =
        tvShowApi.fetchSimilarMovies(id).items.asTVShowDomainModel()
}
