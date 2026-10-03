package com.mdi2.androidtestingsdgku

interface UserRepository {
    fun passwordForEmail(email: String): String?
}

