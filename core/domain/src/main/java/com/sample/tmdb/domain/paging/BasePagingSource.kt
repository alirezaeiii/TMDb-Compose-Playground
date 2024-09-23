package com.sample.tmdb.domain.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.tmdb.common.R
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.utils.TMDbException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

abstract class BasePagingSource<T : TMDbItem>(private val context: Context) : PagingSource<Int, T>() {
    protected abstract suspend fun fetchItems(page: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = fetchItems(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1,
            )
        } catch (exception: IOException) {
            LoadResult.Error(TMDbException(context.getString(R.string.failed_loading_msg)))
        } catch (exception: retrofit2.HttpException) {
            LoadResult.Error(TMDbException(context.getString(R.string.failed_loading_msg)))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }
}
