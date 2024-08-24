package com.fappslab.mbchallenge.features.details.presentation.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import com.fappslab.mbchallenge.libraries.testing.robot.RobotCheck

internal class DetailsScreenRobotCheck(
    override val createComposeRule: ComposeContentTestRule
) : RobotCheck {

    fun checkIfHasExactlyInfoGroupTexts() {
        createComposeRule.onNodeWithText("Binance").assertExists()
        createComposeRule.onNodeWithText("ID: BINANCE").assertExists()
        createComposeRule.onNodeWithText("246,0M").assertExists()
        createComposeRule.onNodeWithText("6,1B").assertExists()
        createComposeRule.onNodeWithText("333,8B").assertExists()
    }

    fun checkIfHasExactlyWebsiteButtonText() {
        createComposeRule.onNodeWithText("Website").assertExists()
        createComposeRule.onNodeWithText("https://www.binance.com/").assertExists()
    }

    fun checkIfHasExactlyButtonText() {
        createComposeRule.onNodeWithText("Go back").assertExists()
    }
}
