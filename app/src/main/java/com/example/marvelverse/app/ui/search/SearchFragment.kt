package com.example.marvelverse.app.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetFragment
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.app.ui.home.HomeEvent
import com.example.marvelverse.app.ui.home.HomeFragmentDirections
import com.example.marvelverse.databinding.FragmentSearchBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.searchEvent.observe(viewLifecycleOwner) { event ->
            event?.let {
                handleEvent(event)
                viewModel.clearEvents()
            }
        }
    }

    private fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ClickCharacterEvent -> handleCharacterClick(event.character)
            is SearchEvent.ClickComicEvent -> handleComicClick(event.comic)
            is SearchEvent.ClickEventEvent -> handleEventClick(event.event)
            else -> {}
        }
    }
    private fun handleCharacterClick(character: Character) {
//        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsCharacterFragment(character)
//        binding.root.findNavController().navigate(direction)
    }
    private fun handleComicClick(comic: Comic) {
//        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsCharacterFragment(character)
//        binding.root.findNavController().navigate(direction)
    }
    private fun handleEventClick(event: Event) {
//        val direction = HomeFragmentDirections.actionHomeFragmentToDetailsCharacterFragment(character)
//        binding.root.findNavController().navigate(direction)
    }
}