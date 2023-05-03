package com.example.marvelverse.app.ui.creators

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentMoreCreatorsBinding

class MoreCreatorsFragment : BaseFragment<FragmentMoreCreatorsBinding>
    (FragmentMoreCreatorsBinding::inflate) {

    private val viewModel: MoreCreatorsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getCreators()

    }
}