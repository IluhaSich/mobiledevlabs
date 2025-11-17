package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.mobiledevlabs.ui.screens.HomeScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireActivity()).apply {
            setContent { MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                HomeScreen()
            } }
        }
    }
}