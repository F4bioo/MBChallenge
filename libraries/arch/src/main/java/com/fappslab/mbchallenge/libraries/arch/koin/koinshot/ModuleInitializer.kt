package com.fappslab.mbchallenge.libraries.arch.koin.koinshot

import org.koin.core.module.Module

object ModuleInitializer {

    val modules = mutableSetOf<Module>()

    fun add(modules: List<Module>) {
        ModuleInitializer.modules.addAll(modules)
    }
}
