package com.fappslab.mbchallenge.features.details.di

import com.fappslab.mbchallenge.core.navigation.DetailsNavigation
import com.fappslab.mbchallenge.features.details.navigation.DetailsNavigationImpl
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.provideFeatureRoute
import org.koin.core.module.Module
import org.koin.dsl.module

internal class DetailsModuleShot : KoinShot() {

    override val additionalModule: Module = module {
        provideFeatureRoute<DetailsNavigation> { DetailsNavigationImpl() }
    }
}
