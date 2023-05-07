package com.example.marvelverse.app.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelverse.app.ui.abstracts.BottomNavFragment
import com.example.marvelverse.databinding.AboutScreenBinding

class AboutFragment :
    BottomNavFragment<AboutScreenBinding>(AboutScreenBinding::inflate) {

    private lateinit var aboutAdapter: AboutAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: AboutViewModel by viewModels()

        aboutAdapter = AboutAdapter(emptyList())

        binding.aboutRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = aboutAdapter
        }

        viewModel.currentItem.observe(viewLifecycleOwner) {
            aboutAdapter.histories = it
            aboutAdapter.notifyDataSetChanged()
        }
    }

}