package com.example.marvelverse.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var bottomNavigationView: BottomNavigationView
    private val navController by lazy {
        binding.fragmentContainerView.getFragment<Fragment>().findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNavigationBar()
        setUpAppBar()
    }

    private fun setUpBottomNavigationBar() {
        bottomNavigationView = binding.bottomNavigationBar
        var currentSelectedItem = R.id.home_nav_group
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }

    private fun setUpAppBar() {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}