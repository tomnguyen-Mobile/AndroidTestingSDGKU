package com.mdi2.androidtestingsdgku

import org.junit.Assert.assertEquals
import org.junit.Test

class LoginValidatorTests {

    @Test
    fun emptyEmail(){
        assertEquals(
            LoginValidator.LoginError.EMPTY_EMAIL,
            LoginValidator.validate("", "Tommi123")
        )
    }

    @Test
    fun invalidEmail(){
        assertEquals(
            LoginValidator.LoginError.INVALID_EMAIL,
            LoginValidator.validate("tom", "Tommi123")
        )
    }

    @Test
    fun shortPassword(){
        assertEquals(
            LoginValidator.LoginError.SHORT_PASSWORD,
            LoginValidator.validate("tom@example.com", "Tom")
        )
    }

    @Test
    fun validEmailAndPassword(){
        assertEquals(
            null,
            LoginValidator.validate("tom@example.com", "Tommi123")
        )
    }
}