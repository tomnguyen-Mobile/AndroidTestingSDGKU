package com.mdi2.androidtestingsdgku;


import com.mdi2.androidtestingsdgku.doubles.FakeUserRepository;

import org.junit.Test;

public class LoginServiceTests {
    @Test
    public void testLogin(){
//        val repo = new FakeUserRepository().withUser("testUser","password123")
        LoginService loginService = new LoginService(new UserStore());
    }
}