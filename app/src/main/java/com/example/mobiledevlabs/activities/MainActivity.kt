package com.example.mobiledevlabs.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.example.mobiledevlabs.R
import com.example.mobiledevlabs.core_ui.BaseActivity

internal class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            FragmentContainerView(this).apply {
                id = R.id.nav_host_fragment
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
        )

        if (savedInstanceState == null) {
            val navHost = NavHostFragment.Companion.create(R.navigation.nav_graph)
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, navHost)
                .setPrimaryNavigationFragment(navHost)
                .commit()
        }
    }
}