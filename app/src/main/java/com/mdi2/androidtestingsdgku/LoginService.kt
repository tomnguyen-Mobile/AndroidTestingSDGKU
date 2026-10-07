package com.mdi2.androidtestingsdgku

class LoginService(private val store: UserRepository = UserStore()){
    fun login(email: String, password: String): LoginResult {
        LoginValidator.validate(email, password)?.let {
            return LoginResult.Invalid(it)
        }
        return if (store.passwordForEmail(email.trim()) == password) {
            LoginResult.Success
        } else {
            LoginResult.WrongCredentials
        }
    }
}