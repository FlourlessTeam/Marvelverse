package com.example.marvelverse.app.ui.stories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentMoreStoriesBinding


class MoreStoriesFragment : BaseFragment<FragmentMoreStoriesBinding>
    (FragmentMoreStoriesBinding::inflate) {

    private val viewModel: MoreStoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getStories()
        val adapter=MoreStoriesAdapter(viewModel)
        binding.storiesRecyclerView.adapter=adapter

    }


}