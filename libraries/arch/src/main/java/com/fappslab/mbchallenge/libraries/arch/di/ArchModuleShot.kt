package com.fappslab.mbchallenge.libraries.arch.di

import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import com.fappslab.mbchallenge.libraries.arch.navigation.FeatureRoute
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FEATURE_ROUTES_QUALIFIER = "feature_routes"

internal class ArchModuleShot : KoinShot() {

    override val additionalModule = module {
        single(named(FEATURE_ROUTES_QUALIFIER)) {
            getAll<FeatureRoute>()
        }
    }
}
