package com.example.mobiledevlabs.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.mobiledevlabs.core_ui.BaseActivity
import com.example.mobiledevlabs.ui.screens.OnboardScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class OnBoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                OnboardScreen(
                    onNext = { toSignIn() }
                )
            }
        }
    }

    private fun toSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}