package com.example.marvelverse.app.ui.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentComicsBinding


class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) {

    private val viewModel: ComicViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getComics()
    }

}