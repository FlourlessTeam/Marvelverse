package com.example.marvelverse.app.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelverse.app.ui.events.EventsFragment
import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		supportFragmentManager.beginTransaction().add(binding.fragmentContainerView.id, EventsFragment()).commit()
	}

}