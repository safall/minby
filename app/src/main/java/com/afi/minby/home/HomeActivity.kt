package com.afi.minby.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.afi.minby.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_launcher.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }

    override fun onSupportNavigateUp() =
        NavHostFragment.findNavController(host_fragment).navigateUp()
}