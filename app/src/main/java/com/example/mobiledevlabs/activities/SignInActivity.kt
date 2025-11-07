package com.example.mobiledevlabs.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.mobiledevlabs.User
import com.example.mobiledevlabs.core_ui.BaseActivity
import com.example.mobiledevlabs.ui.screens.SignInScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val user = intent.getSerializableExtra("user") as? User

            val prefillEmail = rememberSaveable { mutableStateOf(intent.getStringExtra("email") ?: "") }
            val prefillPassword = rememberSaveable { mutableStateOf(intent.getStringExtra("password") ?: "") }

            if (user != null) {
                prefillEmail.value = user.email
                prefillPassword.value = user.password
            }
            MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                SignInScreen(
                    email = prefillEmail.value,
                    password = prefillPassword.value,
                    onEmailChange = { prefillEmail.value = it },
                    onPasswordChange = { prefillPassword.value = it },
                onLogin = { toHome() },
                onSignUp = { toSignUp() }
                )
            }
        }
    }

    private fun toHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun toSignUp() {

        setResult(RESULT_OK, Intent())
        finish()

        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}