package com.example.mobiledevlabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobiledevlabs.data.model.User
import com.example.mobiledevlabs.ui.screens.SignInScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

internal class SignInFragment() : Fragment() {

    private val args: SignInFragmentArgs by navArgs()

    private var prefillEmail: MutableState<String?> = mutableStateOf(null)
    private var prefillPassword: MutableState<String?> = mutableStateOf(null)
    private var user: MutableState<User?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefillEmail.value = args.email
        prefillPassword.value = args.password
        user.value = args.user
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                var email by rememberSaveable { mutableStateOf(args.email.ifEmpty { args.user?.email ?: "" }) }
                var password by rememberSaveable { mutableStateOf(args.password.ifEmpty { args.user?.password ?: "" }) }
                MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                    SignInScreen(
                        email = email,
                        password = password,
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it },
                        onLogin = ::navigateToHome,
                        onSignUp = ::navigateToSignUp
                    )
                }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(SignInFragmentDirections.actionSignInToHome())
    }

    private fun navigateToSignUp() {
        findNavController().navigate(SignInFragmentDirections.actionSignInToSignUp())
    }
}