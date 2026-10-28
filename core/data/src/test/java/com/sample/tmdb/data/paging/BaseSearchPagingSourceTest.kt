package com.sample.tmdb.data.paging

import android.content.Context
import com.sample.tmdb.data.response.NetworkTMDbWrapper
import com.sample.tmdb.data.response.TMDbItemResponse
import com.sample.tmdb.domain.model.TMDbItem
import com.sample.tmdb.domain.paging.BasePagingSource
import org.mockito.Mock

abstract class BaseSearchPagingSourceTest<T : TMDbItem, R : TMDbItemResponse> {

    @Mock
    protected lateinit var context: Context

    protected abstract fun getPagingDataSource(): BasePagingSource<T>

    protected abstract fun mockApiResponse()

    protected abstract suspend fun getApiCall(): NetworkTMDbWrapper<R>
}
