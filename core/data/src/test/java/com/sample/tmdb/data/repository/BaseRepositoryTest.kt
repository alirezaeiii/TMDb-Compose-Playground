package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.test.TestCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

abstract class BaseRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    protected lateinit var context: Context

    protected abstract fun initRepository()

    protected abstract fun mockApiResponse()

    @Before
    fun setup() {
        initRepository()
    }
}