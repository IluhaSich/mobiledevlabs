package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.mobiledevlabs.activities.MainActivity
import com.example.mobiledevlabs.ui.screens.SignUpScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

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
                            val bundle = Bundle().apply {
                                putString(EMAIL, email)
                                putString(PASSWORD, password)
                            }

                            setFragmentResult(
                                requestKey = SignInFragment.KEY,
                                result = bundle
                            )

                            (activity as? MainActivity)?.navigateToSignIn()
                        },
                        onLogin = { (activity as? MainActivity)?.navigateToSignIn() },
                        onSignUpWithObject = { user ->
                            val bundle = Bundle().apply {
                                putSerializable(USER, user)
                            }

                            setFragmentResult(
                                requestKey = SignInFragment.KEY,
                                result = bundle
                            )

                            (activity as? MainActivity)?.navigateToSignIn()
                        }
                    )
                }
            }
        }
    }
    companion object {
        const val USER = "user"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}