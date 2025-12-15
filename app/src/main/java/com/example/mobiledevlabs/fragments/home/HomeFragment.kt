package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mobiledevlabs.ui.screens.HomeScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme
import com.example.mobiledevlabs.ui.fragments.home.HomeViewModel

class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MobiledevlabsTheme {
                    HomeScreen(viewModel = vm)
                }
            }
        }
    }
}
