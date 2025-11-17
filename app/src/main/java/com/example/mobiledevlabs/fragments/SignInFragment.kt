package com.example.mobiledevlabs.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.mobiledevlabs.User
import com.example.mobiledevlabs.activities.MainActivity
import com.example.mobiledevlabs.ui.screens.SignInScreen
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

internal class SignInFragment() : Fragment() {
    private var user: MutableState<User?> = mutableStateOf(null)
    private var prefillEmail: MutableState<String?> = mutableStateOf(null)
    private var prefillPassword: MutableState<String?> = mutableStateOf(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(KEY, this) { key, bundle ->
            prefillEmail.value = bundle.getString(SignUpFragment.EMAIL)
            prefillPassword.value = bundle.getString(SignUpFragment.PASSWORD)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                user.value = bundle.getSerializable(SignUpFragment.USER, User::class.java)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                prefillEmail.value = prefillEmail.value ?: user.value?.email
                prefillPassword.value = prefillPassword.value ?: user.value?.password

                MobiledevlabsTheme(darkTheme = false, dynamicColor = false) {
                    SignInScreen(
                        email = prefillEmail.value ?: "",
                        password = prefillPassword.value ?: "",
                        onEmailChange = { prefillEmail.value = it },
                        onPasswordChange = { prefillPassword.value = it },
                        onLogin = { (activity as? MainActivity)?.navigateToHome() },
                        onSignUp = { (activity as? MainActivity)?.navigateToSignUp() }
                    )
                }
            }
        }
    }
    companion object {
        const val KEY = "Sign In"
    }
}