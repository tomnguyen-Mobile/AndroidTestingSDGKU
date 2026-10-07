package com.mdi2.androidtestingsdgku

class UserStore : UserRepository{
    val users = mapOf("tom@example.com" to "password123")
    override fun passwordForEmail(email: String): String? {
        return users[email]
    }
}