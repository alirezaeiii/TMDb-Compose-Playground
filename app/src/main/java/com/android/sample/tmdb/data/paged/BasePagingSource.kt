package com.android.sample.tmdb.data.paged

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.sample.tmdb.R
import com.android.sample.tmdb.domain.model.TMDbItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

abstract class BasePagingSource<T : TMDbItem>(private val context: Context) : PagingSource<Int, T>() {

    protected abstract suspend fun fetchItems(page: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            LoadResult.Page(
                data = fetchItems(page),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(Exception(context.getString(R.string.failed_loading_msg)))
        } catch (exception: HttpException) {
            LoadResult.Error(Exception(context.getString(R.string.failed_loading_msg)))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}