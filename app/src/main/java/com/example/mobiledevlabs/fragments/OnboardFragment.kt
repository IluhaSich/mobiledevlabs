package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.mobiledevlabs.activities.MainActivity
import com.example.mobiledevlabs.ui.screens.OnboardScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class OnboardFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                    OnboardScreen(
                        onNext = {
                            (activity as? MainActivity)?.navigateToSignIn()
                        }
                    )
                }
            }
        }
    }
}