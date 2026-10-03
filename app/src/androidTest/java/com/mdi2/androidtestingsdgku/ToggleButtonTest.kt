package com.mdi2.androidtestingsdgku

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ToggleButtonTest {
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
//        Thread.sleep(10000)

        button.assertTextEquals("Tapped")
        button.performClick()
//        Thread.sleep(10000)

        button.assertTextEquals("Tap me")
    }
}