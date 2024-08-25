package com.fappslab.mbchallenge.features.exchanges.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.mbchallenge.core.navigation.DetailsNavigation
import com.fappslab.mbchallenge.core.navigation.DetailsRoute
import com.fappslab.mbchallenge.features.exchanges.presentation.setupkoin.KoinTestBase
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewEffect
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewIntent
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.fappslab.mbchallenge.features.exchanges.presentation.viewmodel.ExchangesViewState
import com.fappslab.mbchallenge.libraries.arch.navigation.extension.LocalNavController
import com.fappslab.mbchallenge.libraries.testing.stub.exchangesStub
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
internal class ExchangesScreenKtTest : KoinTestBase() {

    @get:Rule
    val createComposeRule = createComposeRule()

    private val initialState = ExchangesViewState()
    private val navController = mockk<NavHostController>(relaxed = true)
    private val detailNavigation = mockk<DetailsNavigation>(relaxed = true)

    private val fakeState = MutableStateFlow(initialState)
    private val fakeEffect = MutableSharedFlow<ExchangesViewEffect>()
    private val fakeViewModel = mockk<ExchangesViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { effect } returns fakeEffect
    }

    @Before
    fun setUp() {
        loadKoinModules(module { single<DetailsNavigation> { detailNavigation } })
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Composable
    private fun Subject() {
        CompositionLocalProvider(LocalNavController provides navController) {
            ExchangesScreen(
                vm = fakeViewModel
            )
        }
    }

    @Test
    fun when_ExchangesScreen_is_showing_Then_should_display_TopBar_expected_tile() {
        // Given
        val expectedTitle = "Exchanges"

        // When
        createComposeRule.setContent { Subject() }

        // Then
        createComposeRule
            .onNodeWithTag(TOP_BAR_TEST_TAG)
            .onChild()
            .assertTextEquals(expectedTitle)
    }

    @Test
    fun when_ExchangesScreen_is_showing_Then_should_display_EmptyScreen_expected_description() {
        // Given
        val expectedDescription = "No exchanges available at the moment."

        // When
        createComposeRule.setContent { Subject() }

        // Then
        createComposeRule
            .onNodeWithTag(EMPTY_SCREE_DESCRIPTION_TEST_TAG)
            .assertExists()
            .assertTextEquals(expectedDescription)
    }

    @Test
    fun when_ExchangesScreen_is_showing_Then_should_display_EmptyScreen_animation() {
        // When
        createComposeRule.setContent { Subject() }

        // Then
        createComposeRule
            .onNodeWithTag(EMPTY_SCREE_ILLU_TEST_TAG)
            .assertExists()
    }

    @Test
    fun when_tryAgainButton_is_clicked_Then_should_trigger_OnGetExchanges_intent() {
        // Given
        every { fakeViewModel.onViewIntent(any()) } just Runs

        // When
        createComposeRule.setContent { Subject() }

        // Then
        createComposeRule
            .onNodeWithTag(EMPTY_SCREE_TRY_AGAIN_TEST_TAG)
            .performClick()
        verify { fakeViewModel.onViewIntent(ExchangesViewIntent.OnGetExchanges) }
    }

    @Test
    fun when_Item_is_clicked_Then_should_trigger_OnExchangeClicked_intent() {
        // Given
        val exchanges = exchangesStub()
        val exchangeId = exchanges.first().exchangeId
        val route = DetailsRoute(exchangeId)
        fakeState.update { it.copy(exchanges = exchanges) }
        every { detailNavigation.navigateToFeature(navController, route) } just Runs
        every {
            fakeViewModel.onViewIntent(ExchangesViewIntent.OnExchangeClicked(exchangeId))
        } coAnswers {
            fakeEffect.emit(ExchangesViewEffect.NavigateToDetails(exchangeId))
        }

        // When
        createComposeRule.setContent { Subject() }

        // Then
        createComposeRule
            .onNodeWithTag("${EXCHANGES_ITEM_TEST_TAG}_$exchangeId")
            .performClick()
        verify { fakeViewModel.onViewIntent(ExchangesViewIntent.OnExchangeClicked(exchangeId)) }
        verify { detailNavigation.navigateToFeature(navController, route) }
    }
}
