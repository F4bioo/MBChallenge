package com.fappslab.mbchallenge.core.data.coordinator.di

import com.fappslab.mbchallenge.core.data.coordinator.repository.MBChallengeRepositoryImpl
import com.fappslab.mbchallenge.core.domain.repository.MBChallengeRepository
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import org.koin.core.module.Module
import org.koin.dsl.module

internal class CoordinatorModuleShot : KoinShot() {

    override val domainModule: Module = module {
        factory<MBChallengeRepository> {
            MBChallengeRepositoryImpl(
                remoteDataSource = get()
            )
        }
    }
}
