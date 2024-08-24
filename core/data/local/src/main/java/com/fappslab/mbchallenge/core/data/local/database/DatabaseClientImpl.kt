package com.fappslab.mbchallenge.core.data.local.database

import android.content.Context
import androidx.room.Room
import com.fappslab.mbchallenge.core.data.local.database.room.MBChallengeDatabase

internal class DatabaseClientImpl(
    private val context: Context,
    private val databaseName: String
) : DatabaseClient<MBChallengeDatabase> {

    override fun create(): MBChallengeDatabase {
        return Room.databaseBuilder(
            context,
            MBChallengeDatabase::class.java,
            databaseName
        ).build()
    }
}
