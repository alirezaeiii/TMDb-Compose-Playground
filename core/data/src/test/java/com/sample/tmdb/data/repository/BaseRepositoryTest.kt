package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.test.TestCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.lenient

abstract class BaseRepositoryTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    protected lateinit var context: Context

    protected abstract fun initRepository()

    protected abstract fun mockApiResponse()

    protected open fun mockFailApiResponse() {}

    @Before
    fun setup() {
        lenient().`when`(context.getString(anyInt())).thenReturn("Error message")
        initRepository()
    }
}
