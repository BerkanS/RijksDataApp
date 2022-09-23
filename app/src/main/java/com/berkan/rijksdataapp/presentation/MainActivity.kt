package com.berkan.rijksdataapp.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.berkan.rijksdataapp.R
import com.berkan.rijksdataapp.databinding.ActivityMainBinding
import com.berkan.rijksdataapp.presentation.search.detail.SearchDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNav()
    }

    private fun setBottomNav() {
        val navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                TransitionManager.beginDelayedTransition(binding.root as ViewGroup, Slide(Gravity.BOTTOM).excludeTarget(R.id.activity_main_nav_host_fragment, true))
                when (f) {
                    is SearchDetailFragment -> {
                        binding.bottomNavigation.visibility = View.GONE
                    }
                    else -> {
                        binding.bottomNavigation.visibility = View.VISIBLE
                    }
                }
            }
        }, true)

    }
}