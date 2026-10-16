package com.sample.tmdb.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.TrendingPerson
import com.sample.tmdb.common.utils.TMDbException
import com.sample.tmdb.data.network.PersonService
import com.sample.tmdb.data.response.asDomainModel
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PersonPagingSource(
    private val personApi: PersonService,
    private val context: Context,
    private val query: String? = null,
) : PagingSource<Int, TrendingPerson>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingPerson> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = (
                query?.let {
                    personApi.getTrendingPeople(it, page)
                } ?: personApi.getTrendingPeople(page)
                ).results.asDomainModel()

            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1,
            )
        } catch (_: IOException) {
            LoadResult.Error(TMDbException(context.getString(R.string.failed_loading_msg)))
        } catch (_: retrofit2.HttpException) {
            LoadResult.Error(TMDbException(context.getString(R.string.failed_loading_msg)))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TrendingPerson>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }
}
