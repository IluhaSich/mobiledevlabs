package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mobiledevlabs.R
import com.example.mobiledevlabs.data.CharacterRepository
import com.example.mobiledevlabs.data.network.HttpClientProvider
import com.example.mobiledevlabs.fragments.home.HomeViewModelFactory
import com.example.mobiledevlabs.ui.screens.HomeScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme
import com.example.mobiledevlabs.fragments.home.HomeViewModel

class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels {
        HomeViewModelFactory(CharacterRepository(HttpClientProvider.client, requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MobiledevlabsTheme {
                    HomeScreen(
                        viewModel = vm,
                        onSettingsClick = {
                            findNavController().navigate(R.id.action_home_to_settings)
                        }
                    )
                }
            }
        }
    }
}
