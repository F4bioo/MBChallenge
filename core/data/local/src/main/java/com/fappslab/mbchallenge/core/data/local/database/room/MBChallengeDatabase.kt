package com.fappslab.mbchallenge.core.data.local.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fappslab.mbchallenge.core.data.local.dao.ExchangesDao
import com.fappslab.mbchallenge.core.data.local.model.ExchangeEntity

@Database(entities = [ExchangeEntity::class], version = 1, exportSchema = false)
internal abstract class MBChallengeDatabase : RoomDatabase() {
    abstract fun exchangesDao(): ExchangesDao
}
