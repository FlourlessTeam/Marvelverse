package com.example.marvelverse.app.ui.about

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.AboutScreenBinding
import com.example.marvelverse.databinding.FragmentCharactersBinding

class AboutFragment:
    BaseFragment<AboutScreenBinding>(AboutScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: AboutViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}