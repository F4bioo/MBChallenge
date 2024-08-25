package com.fappslab.mbchallenge.core.data.remote.di

import com.fappslab.mbchallenge.libraries.testing.rules.KoinModuleTest
import com.fappslab.mbchallenge.libraries.testing.rules.MainCoroutineTestRule
import io.mockk.mockk
import okhttp3.Interceptor
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal class RemoteModuleShotTest : KoinModuleTest(RemoteModuleShot()) {

    @get:Rule
    override val coroutineTestRule: MainCoroutineTestRule = MainCoroutineTestRule()

    override val mockedModules = module {
        factory<List<Interceptor>>(named(COIN_API_INTERCEPTORS_QUALIFIER)) { mockk(relaxed = true) }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke RemoteModuleShot`() {
        checkModules()
    }
}
