package com.fappslab.mbchallenge.libraries.testing.rules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class LocalTestRule : TestWatcher() {

    private val context: Context = ApplicationProvider.getApplicationContext()
    private var database: RoomDatabase? = null

    override fun finished(description: Description) {
        super.finished(description)
        database?.close()
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T : RoomDatabase> createTestDatabase(): T {
        database = Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            T::class.java
        ).allowMainThreadQueries().build()

        return database as T
    }
}
