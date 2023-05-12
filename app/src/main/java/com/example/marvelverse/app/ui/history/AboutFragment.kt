package com.example.marvelverse.app.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.base.BottomNavFragment
import com.example.marvelverse.databinding.AboutScreenBinding

class AboutFragment :
    BottomNavFragment<AboutScreenBinding>(AboutScreenBinding::inflate) {

    private lateinit var aboutAdapter: AboutAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: AboutViewModel by viewModels()
        aboutAdapter = AboutAdapter()
        binding.aboutRecyclerView.adapter = aboutAdapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}