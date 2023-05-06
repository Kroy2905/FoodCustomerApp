package com.foodApp.customerapp.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.foodApp.customerapp.R
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityLandingPageBinding

import com.foodApp.customerapp.viewmodels.LandingViewModel
class LandingPageActivity :  BaseActivity<ActivityLandingPageBinding, LandingViewModel>(
    LandingViewModel::class.java,
    { inflater -> ActivityLandingPageBinding.inflate(inflater) },
){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun setupViews() {



        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_landing_page)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_profile,R.id.navigation_grab,R.id.navigation_settings, R.id.navigation_notifications
            )
        )
        // Remove the ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    //    setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}