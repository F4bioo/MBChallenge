package com.fappslab.mbchallenge.libraries.arch.koin.koinlazy

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.fappslab.mbchallenge.libraries.arch.koin.koinload.KoinLoad
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@SuppressLint("ComposableNaming")
@OptIn(KoinExperimentalAPI::class)
@Composable
fun KoinLazyModuleInitializer(
    koinLoad: KoinLoad,
    unloadOnForgotten: Boolean? = null,
    unloadOnAbandoned: Boolean? = null,
    unloadModules: Boolean = false
) {
    rememberKoinModules(
        unloadOnForgotten = unloadOnForgotten,
        unloadOnAbandoned = unloadOnAbandoned,
        unloadModules = unloadModules,
        modules = { koinLoad.modules }
    )
}
