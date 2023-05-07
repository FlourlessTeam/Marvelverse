package com.example.marvelverse.app.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelverse.app.ui.abstracts.BottomNavFragment
import com.example.marvelverse.databinding.FragmentSearchBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event

class SearchFragment : BottomNavFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.searchEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }

    }

    private fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ClickCharacterEvent -> navigateToDetails(event.character)
            is SearchEvent.ClickComicEvent -> navigateToDetails(event.comic)
            is SearchEvent.ClickEventEvent -> navigateToDetails(event.event)
            else -> {}
        }
        viewModel.clearEvents()
    }

    private fun <T> navigateToDetails(element: T) {
        when (element) {
            is Character -> findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsCharacterFragment(element))
            is Comic -> findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToComicDetailsFragment(element))
            is Event -> findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToEventDetailsFragment(element))
            else -> {}
        }

    }


}