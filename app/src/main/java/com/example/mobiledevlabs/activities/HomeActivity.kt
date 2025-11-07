package com.example.mobiledevlabs.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.mobiledevlabs.core_ui.BaseActivity
import com.example.mobiledevlabs.ui.screens.HomeScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                HomeScreen()
            }
        }
    }
}