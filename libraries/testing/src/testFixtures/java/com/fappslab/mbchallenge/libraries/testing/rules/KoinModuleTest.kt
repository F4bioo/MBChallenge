package com.fappslab.mbchallenge.libraries.testing.rules

import com.fappslab.mbchallenge.libraries.arch.koin.KoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.ParametersBinding
import org.koin.test.check.checkKoinModules

abstract class KoinModuleTest(private val koinModules: KoinModules) : KoinTest {

    abstract val coroutineTestRule: MainCoroutineTestRule

    protected open val mockedModules: Module = module {}

    protected open fun KoinModuleTest.checkModules(checkBlock: ParametersBinding.() -> Unit = {}) {
        checkKoinModules(modules = koinModules.modules + mockedModules, parameters = checkBlock)
    }
}
