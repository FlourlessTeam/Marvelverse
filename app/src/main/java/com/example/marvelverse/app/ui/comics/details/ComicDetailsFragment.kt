package com.example.marvelverse.app.ui.comics.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentComicDetailsBinding

class ComicDetailsFragment : BaseFragment<FragmentComicDetailsBinding>(FragmentComicDetailsBinding::inflate)
        {
        private val viewModel: ComicsDetailsViewModel by viewModels()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.viewModel = viewModel
            binding.lifecycleOwner = this
            binding.titleRecycler.adapter = CharactersAdapter(listOf(), this)
            binding.title2Recycler.adapter = EventAdapter(listOf(), this)
            viewModel.getEvent(comic.events.collectionURI)
            viewModel.getCharacter(comic.characters.collectionURI)

        }

    }