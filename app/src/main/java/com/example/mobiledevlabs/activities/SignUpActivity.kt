package com.example.mobiledevlabs.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mobiledevlabs.User
import com.example.mobiledevlabs.core_ui.BaseActivity
import com.example.mobiledevlabs.ui.screens.SignUpScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class SignUpActivity : BaseActivity() {

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val user = result.data?.getSerializableExtra("user") as? User
                if (user != null) {
                    Log.d("SignUpActivity", "Вернулся пользователь: $user")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                SignUpScreen(
                    onSignUp = { firstName, lastName, email, password ->
                        onSignUp(firstName, lastName, email, password)
                    },
                    onSignUpWithObject = { user ->
                        onSignUpWithUserObject(user)
                    },
                    onLogin = {
                        onLogin()
                    }
                )
            }
        }
    }

    private fun onSignUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        val intent = Intent(this, SignInActivity::class.java).apply {
            putExtra("name", firstName)
            putExtra("last", lastName)
            putExtra("email", email)
            putExtra("password", password)
        }

        signInLauncher.launch(intent)
    }

    private fun onSignUpWithUserObject(user: User) {
        val intent = Intent(this, SignInActivity::class.java).apply {
            putExtra("user", user)
        }
        signInLauncher.launch(intent)
    }

    private fun onLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        signInLauncher.launch(intent)
    }
}
