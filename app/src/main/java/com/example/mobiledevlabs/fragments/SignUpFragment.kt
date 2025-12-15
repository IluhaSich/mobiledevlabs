package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.mobiledevlabs.R
import com.example.mobiledevlabs.activities.MainActivity
import com.example.mobiledevlabs.ui.screens.SignUpScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme
import androidx.navigation.fragment.findNavController
import com.example.mobiledevlabs.User

internal class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                    SignUpScreen(
                        onSignUp = { firstName, lastName, email, password ->
                            navigateToSignIn(
                                email = email,
                                password = password
                            )
                        },
                        onLogin = { navigateToSignIn() },
                        onSignUpWithObject = { user -> navigateToSignIn(user = user) }
                    )
                }
            }
        }
    }

    private fun navigateToSignIn(
        email: String = "",
        password: String = "",
        user: User? = null
    ) {
        val direction = SignUpFragmentDirections.actionSignUpToSignIn(
            email = email,
            password = password,
            user = user
        )

        findNavController().navigate(direction)
    }
}