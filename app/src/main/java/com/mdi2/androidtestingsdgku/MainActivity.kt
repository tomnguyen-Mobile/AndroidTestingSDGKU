package com.mdi2.androidtestingsdgku

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputEmail = findViewById<TextInputEditText>(R.id.emailTitle)
        val inputPassword = findViewById<TextInputEditText>(R.id.passwordTitle)
        val titleEmail = findViewById<TextInputLayout>(R.id.emailTitle)
        val titlePassword = findViewById<TextInputLayout>(R.id.passwordTitle)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            titleEmail.error = null
            titlePassword.error = null
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            when (LoginValidator.validate(email, password)) {
                LoginValidator.LoginError.EMPTY_EMAIL -> titleEmail.error = "Email is required"
                LoginValidator.LoginError.INVALID_EMAIL -> titleEmail.error = "Email is invalid"
                LoginValidator.LoginError.SHORT_PASSWORD -> titlePassword.error =
                    "Password must be at least 8 characters"
                null -> {
                    titleEmail.error = null
                    titlePassword.error = null
                    goToShop()
                }
            }
        }
    }
    fun goToShop() {
        startActivity((Intent(this, ShopActivity::class.java)))
    }
}