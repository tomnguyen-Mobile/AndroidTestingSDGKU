package com.mdi2.androidtestingsdgku

class UserStore : UserRepository{
    val users = mapOf("Tom@example.com" to "password123")
    override fun passwordForEmail(email: String): String? {
        return users[email]
    }
}