package com.sample.tmdb.data.paging

import android.content.Context
import androidx.room.withTransaction
import com.sample.tmdb.common.test.TestCoroutineRule
import com.sample.tmdb.data.source.local.TMDbDatabase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseRemoteMediatorTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    protected val database: TMDbDatabase = mockk(relaxed = true)

    protected val context: Context = mockk(relaxed = true)

    @Before
    open fun setupBase() {
        mockkStatic("androidx.room.RoomDatabaseKt")
        val blockSlot = slot<suspend () -> Any?>()
        coEvery { database.withTransaction(capture(blockSlot)) } coAnswers {
            blockSlot.captured.invoke()
        }
    }

    @After
    open fun tearDownBase() {
        unmockkStatic("androidx.room.RoomDatabaseKt")
    }
}
