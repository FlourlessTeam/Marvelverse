package com.example.marvelverse.app.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.comics.ComicsFragment
import com.example.marvelverse.app.ui.events.EventsFragment
import com.example.marvelverse.app.ui.stories.MoreStoriesFragment
import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView, EventsFragment())
        transaction.commit()
    }

}