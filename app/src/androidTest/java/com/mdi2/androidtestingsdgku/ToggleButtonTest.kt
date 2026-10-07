package com.mdi2.androidtestingsdgku

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ToggleButtonTest {
//    @get: Rule val composeRule = createComposeRule()
    @get:Rule
    val composeRule = createAndroidComposeRule<ShopActivity>()
//
//    @Test
//    fun buttonStartsWithTabMe(){
//        composeRule.setContent {
//            ToggleButton()
//        }
//        composeRule
//            .onNodeWithTag(ToggleButtonTag.TAG)
//            .assertTextEquals("Tap me")
//    }

    @Test
    fun addToCartTest(){
        val button = composeRule.onNodeWithTag("add_1")
        button.performClick()
        composeRule.onNodeWithTag(testTag="cart_item_count", useUnmergedTree = true).assertTextEquals("Cart (1)")
    }

    @Test
    fun addToCart2Test(){
        val button = composeRule.onNodeWithTag("add_2")
        button.performClick()
        composeRule.onNodeWithTag(testTag="cart_item_count", useUnmergedTree = true).assertTextEquals("Cart (1)")
    }

    @Test
    fun productListRendersCorrectlyTest() {
        composeRule.onNodeWithTag("product_list").onChildren().assertCountEquals(5*3) // check total count 5 children * 3 items = 15

        composeRule.onNodeWithTag("product_list_1").assertExists()
        composeRule.onNodeWithTag("product_list_2").assertExists()
        composeRule.onNodeWithTag("product_list_3").assertExists()
        composeRule.onNodeWithTag("product_list_4").assertExists()
        composeRule.onNodeWithTag("product_list_5").assertExists()
    }

    @Test
    fun checkOutCartTest(){
        composeRule.onNodeWithTag("add_1").performClick()
        composeRule.onNodeWithTag("add_1").performClick()
        composeRule.onNodeWithTag("add_1").performClick()

        val button = composeRule.onNodeWithTag("go_to_cart_button")
        button.performClick()
        composeRule.onNodeWithTag("cart_total").assertTextEquals("Total items: 3, Total price: $30.00")
    }

    @Test
    fun shoppingCartShowsAllElementsTest(){
        composeRule.onNodeWithTag("shop_title").assertIsDisplayed()
        composeRule.onNodeWithTag("cart_item_count", useUnmergedTree = true).assertTextEquals("Cart (0)")
        composeRule.onNodeWithTag("go_to_cart_button").performClick()
        composeRule.onNodeWithTag("cart_title").assertIsDisplayed()
        composeRule.onNodeWithTag("empty_cart").assertIsDisplayed()
    }
}
//    @Test
//    fun buttonTogglesTextOnClick(){
//        composeRule.setContent {
//            ToggleButton()
//        }
//        val button = composeRule.onNodeWithTag(ToggleButtonTag.TAG)
//        button.assertTextEquals("Tap me")
//        button.performClick()
////        Thread.sleep(10000)
//
//        button.assertTextEquals("Tapped")
//        button.performClick()
////        Thread.sleep(10000)
//
//        button.assertTextEquals("Tap me")
//    }
