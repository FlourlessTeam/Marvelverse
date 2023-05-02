package com.example.marvelverse.app.ui.viewAll

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentViewAllCreatorsBinding

class ViewAllCreatorsFragment: BaseFragment<FragmentViewAllCreatorsBinding>
    (FragmentViewAllCreatorsBinding::inflate) {

    private val viewModel:MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel=viewModel
        binding.lifecycleOwner=this

    }
}