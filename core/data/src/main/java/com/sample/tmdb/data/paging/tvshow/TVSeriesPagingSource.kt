package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.data.utils.Constants.AMAZON_PRIME_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.APPLE_TV_PROVIDER_ID
import com.sample.tmdb.data.utils.Constants.NETFLIX_PROVIDER_ID
import com.sample.tmdb.domain.model.SortType
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource

class TVSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService,
    private val sortType: SortType,
    private val tvShowId: Int? = null,
) : BasePagingSource<TVShow>(context) {
    override suspend fun fetchItems(page: Int): List<TVShow> = when (sortType) {
        SortType.TRENDING -> tvShowApi.trendingTVSeries(page).items.asTVShowDomainModel()
        SortType.DISCOVER -> tvShowApi.discoverTVSeries(page).items.asTVShowDomainModel()
        SortType.MOST_POPULAR -> tvShowApi.popularTVSeries(page).items.asTVShowDomainModel()
        SortType.NOW_PLAYING -> tvShowApi.airingTodayTVSeries(page).items.asTVShowDomainModel()
        SortType.UPCOMING -> tvShowApi.onTheAirTVSeries(page).items.asTVShowDomainModel()
        SortType.HIGHEST_RATED -> tvShowApi.topRatedTVSeries(page).items.asTVShowDomainModel()
        SortType.SIMILAR -> tvShowApi.fetchSimilarTVSeries(tvShowId!!, page).items.asTVShowDomainModel()
        SortType.APPLE_TV -> tvShowApi.providerTVSeries(APPLE_TV_PROVIDER_ID, page).items.asTVShowDomainModel()
        SortType.NETFLIX -> tvShowApi.providerTVSeries(NETFLIX_PROVIDER_ID, page).items.asTVShowDomainModel()
        SortType.AMAZON_PRIME -> tvShowApi.providerTVSeries(AMAZON_PRIME_PROVIDER_ID, page).items.asTVShowDomainModel()
    }
}
