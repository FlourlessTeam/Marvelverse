package com.example.marvelverse.app.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}