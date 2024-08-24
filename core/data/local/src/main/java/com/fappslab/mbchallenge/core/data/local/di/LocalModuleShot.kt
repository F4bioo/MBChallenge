package com.fappslab.mbchallenge.core.data.local.di

import com.fappslab.mbchallenge.core.data.local.BuildConfig
import com.fappslab.mbchallenge.core.data.local.dao.ExchangesDao
import com.fappslab.mbchallenge.core.data.local.database.DatabaseClient
import com.fappslab.mbchallenge.core.data.local.database.DatabaseClientImpl
import com.fappslab.mbchallenge.core.data.local.database.room.MBChallengeDatabase
import com.fappslab.mbchallenge.core.data.local.repository.LocalRepositoryImpl
import com.fappslab.mbchallenge.core.data.local.source.LocalDataSourceImpl
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LocalModuleShot : KoinShot() {

    override val dataModule: Module = module {
        single<DatabaseClient<MBChallengeDatabase>> {
            DatabaseClientImpl(
                context = androidApplication(),
                databaseName = BuildConfig.DATABASE_NAME
            )
        }
        single<ExchangesDao> {
            get<DatabaseClient<MBChallengeDatabase>>().create().exchangesDao()
        }

        factory<MBChallengeRepository> {
            LocalRepositoryImpl(
                dataSource = LocalDataSourceImpl(dao = get()),
            )
        }
    }
}
