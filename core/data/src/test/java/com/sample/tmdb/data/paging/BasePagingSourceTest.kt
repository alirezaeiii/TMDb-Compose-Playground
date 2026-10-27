package com.sample.tmdb.data.paging

import android.content.Context
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TMDbItemResponse
import com.sample.tmdb.domain.paging.BasePagingSource
import com.sample.tmdb.domain.model.SortType
import org.mockito.Mock

abstract class BasePagingSourceTest<T : TMDbItem, R : TMDbItemResponse> {

    @Mock
    protected lateinit var context: Context

    protected abstract fun getPagingDataSource(sortType: SortType): BasePagingSource<T>

    protected abstract fun mockApiResponse(sortType: SortType)

    protected abstract suspend fun getApiCall(sortType: SortType): NetworkTMDbWrapper<R>
}
