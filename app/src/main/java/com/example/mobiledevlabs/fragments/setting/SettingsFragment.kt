package com.example.mobiledevlabs.fragments.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mobiledevlabs.fragments.setting.SettingsViewModel
import com.example.mobiledevlabs.ui.screens.SettingsScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

class SettingsFragment : Fragment() {

    private val vm: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MobiledevlabsTheme {
                    SettingsScreen(
                        viewModel = vm,
                        onBack = { findNavController().popBackStack() }
                    )
                }
            }
        }
    }
}
