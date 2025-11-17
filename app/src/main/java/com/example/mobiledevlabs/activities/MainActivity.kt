package com.example.mobiledevlabs.activities

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.mobiledevlabs.core_ui.BaseActivity
import com.example.mobiledevlabs.fragments.HomeFragment
import com.example.mobiledevlabs.fragments.OnboardFragment
import com.example.mobiledevlabs.fragments.SignInFragment
import com.example.mobiledevlabs.fragments.SignUpFragment

internal class MainActivity : BaseActivity(){
    private val containerId = View.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = FrameLayout(this).apply {
            id = containerId
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        setContentView(container)

        if (savedInstanceState == null) { navigateToOnboard() }
    }

    private fun navigateToOnboard() {
        supportFragmentManager.commit {
            replace(
                containerId,
                OnboardFragment()
            )
        }
    }
    fun navigateToHome() {
        supportFragmentManager.commit {
            replace(containerId, HomeFragment())
            addToBackStack(HOME)
        }
    }

    fun navigateToSignIn() {
        supportFragmentManager.popBackStack(SIGN_IN, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.commit {
            replace(containerId, SignInFragment())
            addToBackStack(SIGN_IN)
        }
    }

    fun navigateToSignUp() {
        supportFragmentManager.popBackStack(SIGN_UP, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.commit {
            replace(containerId, SignUpFragment())
            addToBackStack(SIGN_UP)
        }
    }


    companion object {
        const val ONBOARD = "Onboard fragment"
        const val SIGN_IN = "SignIn fragment"
        const val SIGN_UP = "SignUp fragment"
        const val HOME = "Home fragment"
    }
}