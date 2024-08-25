package com.fappslab.mbchallenge.features.details.presentation.compose

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.mbchallenge.core.navigation.DetailsRoute
import com.fappslab.mbchallenge.core.navigation.ExchangesNavigation
import com.fappslab.mbchallenge.features.details.presentation.robot.DetailsScreenRobotArrange
import com.fappslab.mbchallenge.features.exchanges.presentation.setupkoin.KoinTestBase
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.LocalNavController
import com.fappslab.mbchallenge.libraries.testing.robot.givenArrange
import com.fappslab.mbchallenge.libraries.testing.robot.thenCheck
import com.fappslab.mbchallenge.libraries.testing.robot.whenAction
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
internal class DetailsScreenKtTest : KoinTestBase() {

    @get:Rule
    val createComposeRule = createComposeRule()

    private val navController = mockk<NavHostController>(relaxed = true)
    private val exchangesNavigation = mockk<ExchangesNavigation>(relaxed = true)

    private val robotScreen = DetailsScreenRobotArrange(createComposeRule) {
        CompositionLocalProvider(LocalNavController provides navController) {
            DetailsScreen(
                exchangeId = "BINANCE",
                vm = it
            )
        }
    }

    @Before
    fun setUp() {
        loadKoinModules(module { single<ExchangesNavigation> { exchangesNavigation } })
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun when_DetailsScreen_is_loaded_Then_should_display_card_elements_correctly() {
        robotScreen
            .givenArrange { loadingExchangesArrangement() }
            .whenAction()
            .thenCheck { checkIfHasExactlyInfoGroupTexts() }
    }

    @Test
    fun when_DetailsScreen_is_loaded_Then_should_display_website_button() {
        robotScreen
            .givenArrange { websiteClickedArrange() }
            .whenAction { websiteClickedAction() }
            .thenCheck { checkIfHasExactlyWebsiteButtonText() }
    }

    @Test
    fun when_DetailsScreen_is_loaded_Then_should_display_go_back_button() {
        robotScreen
            .givenArrange { goBackClickedArrange() }
            .whenAction { goBackClickedAction() }
            .thenCheck { checkIfHasExactlyButtonText() }

        verify { navController.popBackStack<DetailsRoute>(inclusive = true) }
    }
}
