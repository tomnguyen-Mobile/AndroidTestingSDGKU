package com.mdi2.androidtestingsdgku.doubles

import com.mdi2.androidtestingsdgku.UserRepository

class FakeUserRepository : UserRepository {
    private val users = mutableMapOf<String, String>()

    fun withUser(email: String, password: String) = apply {
        users[email] = password
    }

    override fun passwordForEmail(email: String): String? {
        return users[email]
    }

}