package com.fappslab.mbchallenge.features.details.presentation.robot

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewEffect
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewIntent
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.mbchallenge.features.details.presentation.viewmodel.DetailsViewState
import com.fappslab.mbchallenge.libraries.testing.robot.RobotArrange
import com.fappslab.mbchallenge.libraries.testing.stub.exchangeStub
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class DetailsScreenRobotArrange(
    override val createComposeRule: ComposeContentTestRule,
    override val subject: @Composable (viewModel: DetailsViewModel) -> Unit
) : RobotArrange<DetailsScreenRobotAction, DetailsViewModel> {

    private val fakeState = MutableStateFlow(DetailsViewState(exchangeId = "MERCADOBITCOIN"))
    private val fakeEffect = MutableSharedFlow<DetailsViewEffect>()
    override val fakeViewModel = mockk<DetailsViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { effect } returns fakeEffect
    }

    fun loadingExchangesArrangement() {
        fakeState.update { it.copy(exchange = exchangeStub()) }
    }

    fun websiteClickedArrange() {
        loadingExchangesArrangement()
        every {
            fakeViewModel.onViewIntent(DetailsViewIntent.OnBackClicked)
        } coAnswers {
            fakeEffect.emit(DetailsViewEffect.NavigateToExchanges)
        }
    }

    fun goBackClickedArrange() {
        every {
            fakeViewModel.onViewIntent(DetailsViewIntent.OnBackClicked)
        } coAnswers {
            fakeEffect.emit(DetailsViewEffect.NavigateToExchanges)
        }
    }
}
