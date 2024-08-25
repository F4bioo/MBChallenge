package com.fappslab.mbchallenge.features.details.presentation.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import com.fappslab.mbchallenge.libraries.testing.robot.RobotCheck

internal class DetailsScreenRobotCheck(
    override val createComposeRule: ComposeContentTestRule
) : RobotCheck {

    fun checkIfHasExactlyInfoGroupTexts() {
        createComposeRule.onNodeWithText("Mercado Bitcoin").assertExists()
        createComposeRule.onNodeWithText("ID: MERCADOBITCOIN").assertExists()
        createComposeRule.onNodeWithText("228.67").assertExists()
        createComposeRule.onNodeWithText("829.7K").assertExists()
        createComposeRule.onNodeWithText("192.7M").assertExists()
    }

    fun checkIfHasExactlyWebsiteButtonText() {
        createComposeRule.onNodeWithText("Website").assertExists()
        createComposeRule.onNodeWithText("https://www.mercadobitcoin.com.br/").assertExists()
    }

    fun checkIfHasExactlyButtonText() {
        createComposeRule.onNodeWithText("Go back").assertExists()
    }
}
