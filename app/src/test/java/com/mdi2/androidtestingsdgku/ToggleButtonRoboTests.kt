package com.mdi2.androidtestingsdgku

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [35])
class ToggleButtonRoboTests {
    @get: Rule val composeRule = createComposeRule()

    @Test
    fun buttonStartsWithTabMe(){
        composeRule.setContent {
            ToggleButton()
        }
        composeRule
            .onNodeWithTag(ToggleButtonTag.TAG)
            .assertTextEquals("Tap me")
    }

    @Test
    fun buttonTogglesTextOnClick(){
        composeRule.setContent {
            ToggleButton()
        }
        val button = composeRule.onNodeWithTag(ToggleButtonTag.TAG)
        button.assertTextEquals("Tap me")
        button.performClick()
        // Thread.sleep(1000)
        button.assertTextEquals("Tapped")
        button.performClick()

        button.assertTextEquals("Tap me")
    }
}