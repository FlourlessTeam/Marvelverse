package com.example.marvelverse.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNavigationBar()
    }

   private fun setUpBottomNavigationBar(){
  bottomNavigationView = binding.bottomNavigationBar
       bottomNavigationView.setOnItemSelectedListener  { item ->
           when (item.itemId) {
               R.id.navigation_home -> {

                   true
               }
               R.id.navigation_search -> {

                   true
               }
               R.id.navigation_settings -> {
                   true
               }
               else -> false
           }
       }
   }


}