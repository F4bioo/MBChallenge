package com.fappslab.mbchallenge.features.exchanges.di

import com.fappslab.mbchallenge.core.navigation.ExchangesNavigation
import com.fappslab.mbchallenge.features.exchanges.navigation.ExchangesNavigationImpl
import com.fappslab.mbchallenge.libraries.arch.koin.koinshot.KoinShot
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.provideFeatureRoute
import org.koin.core.module.Module
import org.koin.dsl.module

internal class ExchangesModuleShot : KoinShot() {

    override val additionalModule: Module = module {
        provideFeatureRoute<ExchangesNavigation> { ExchangesNavigationImpl() }
    }
}
