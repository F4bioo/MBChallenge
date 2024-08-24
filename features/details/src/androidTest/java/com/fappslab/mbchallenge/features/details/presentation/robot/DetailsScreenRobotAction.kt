package com.fappslab.mbchallenge.features.details.presentation.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fappslab.mbchallenge.features.details.presentation.compose.ON_BACK_CLICKED_TEST_TAG
import com.fappslab.mbchallenge.features.details.presentation.compose.WEBSITE_CLICKED_TEST_TAG
import com.fappslab.mbchallenge.libraries.testing.robot.RobotAction

internal class DetailsScreenRobotAction(
    override val createComposeRule: ComposeContentTestRule
) : RobotAction<DetailsScreenRobotCheck> {

    fun websiteClickedAction() {
        createComposeRule.onNodeWithTag(WEBSITE_CLICKED_TEST_TAG).performClick()
    }

    fun goBackClickedAction() {
        createComposeRule.onNodeWithTag(ON_BACK_CLICKED_TEST_TAG).performClick()
    }
}
