package com.mdi2.androidtestingsdgku

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mdi2.androidtestingsdgku.TextInputLayoutMatchers.hasTextInputLayoutError
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.function.Predicate.not


class LoginPage{
    val emailTitle = onView(withId(R.id.emailTitle))
    val passwordTitle = onView(withId(R.id.passwordTitle))
    val emailInput = onView(withId(R.id.emailInput))
    val passwordInput = onView(withId(R.id.passwordInput))
    val loginButton = onView(withId(R.id.loginButton))
}

@RunWith(AndroidJUnit4::class)
class LoginViewTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val loginPage = LoginPage()

    @Before
    fun setUp(){
        Intents.init()
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown(){
        scenario.close()
        Intents.release()
    }

    @Test
    fun loginScreen_showsAllComponents(){
        loginPage.emailTitle.check(matches(isDisplayed()))
        loginPage.passwordTitle.check(matches(isDisplayed()))
        loginPage.emailInput.check(matches(isDisplayed()))
        loginPage.passwordInput.check(matches(isDisplayed()))
        loginPage.loginButton.check(matches(isDisplayed()))
    }

    @Test
    fun emptyEmail_showsError(){
//        loginPage.emailInput()
        loginPage.loginButton.perform(click())
//        onView(withId(R.id.emailInput)).perform(click())
//        Thread.sleep(1000)
        onView(withId(R.id.emailTitle)).check(matches(hasTextInputLayoutError("Email is required")))
    }

    @Test
    fun loginWithValidCredentials_navigatesToShop() {
        loginPage.emailInput.perform(typeText("tom@example.com"))
        loginPage.passwordInput.perform(typeText("password123"), closeSoftKeyboard())
        loginPage.loginButton.perform(click())
//        Thread.sleep(1000)
        composeRule.onNodeWithTag("shop_title")
            .assertIsDisplayed()
//        loginPage.emailTitle.check(doesNotExist())
    }
}
