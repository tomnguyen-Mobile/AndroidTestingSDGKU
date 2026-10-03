package com.mdi2.androidtestingsdgku

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mdi2.androidtestingsdgku.TextInputLayoutMatchers.hasTextInputLayoutError
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

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
        onView(withId(R.id.emailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.emailInput)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordInput)).check(matches(isDisplayed()))
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyEmail_showsError(){
        onView(withId(R.id.emailInput)).perform(click())
        onView(withId(R.id.emailTitle)).check(matches(hasTextInputLayoutError("Email is required")))
    }

}
