package com.mdi2.androidtestingsdgku

import com.mdi2.androidtestingsdgku.doubles.FakeUserRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Test

class LoginServiceTestDoubles {
    // test with a FAKE user
    @Test
    fun testLogin(){
        val repo = FakeUserRepository().withUser("testUser@123.com","password123")
        val loginService = LoginService(repo)

        val result = loginService.login("testUser@123.com", "password123")

        assertEquals(LoginResult.Success, result)
    }


    @Test
    fun testWrongPasswordReturnsWrongCredentials(){
        val repo = mockk<UserRepository>()
        every { repo.passwordForEmail("testUser@123.com")} returns "password123"

        val service = LoginService(repo)
        val result = service.login("testUser@123.com", "wrongPassword")
        assertEquals(LoginResult.WrongCredentials, result)
    }


    @Test
    fun testEmailNeverCallsRepository(){
        //arrange
        val repo = mockk<UserRepository>()
        //act
        LoginService(repo).login("invalid-email","password123")

        //assert
        verify(exactly = 0 ) { repo.passwordForEmail(any())}
        confirmVerified(repo)
    }

    @Test
    fun validAttemptLooksUpOnceWithTrimmedEmail(){
        val repo = mockk<UserRepository>()
        every { repo.passwordForEmail("testUser@123.com")} returns "password123"

        val service = LoginService(repo)
        service.login("testUser@123.com", "password123")
        verify(exactly=1){ repo.passwordForEmail("testUser@123.com")}
        confirmVerified(repo)
    }
}


























