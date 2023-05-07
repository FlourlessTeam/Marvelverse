package com.example.marvelverse.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.marvelverse.app.ui.abstracts.BottomNavFragment
import com.example.marvelverse.app.ui.home.adapter.HomeAdapter
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series

class HomeFragment : BottomNavFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.homeEvents.observe(viewLifecycleOwner) { event ->
            event?.let {
                handleEvent(event)
                viewModel.clearEvents()
            }
        }
    }

    private fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ClickCharacterEvent -> handleCharacterClick(event.character)
            is HomeEvent.ClickComicEvent -> handleComicClick(event.comic)
            is HomeEvent.ClickEventEvent -> handleEventClick(event.event)
            is HomeEvent.ClickSeriesEvent -> handleSeriesClick(event.series)
            HomeEvent.ClickSeeAllComicsEvent -> handleSeeAllComicsClick()
            HomeEvent.ClickSeeAllEventsEvent -> handleSeeAllEventsClick()
            HomeEvent.ClickSeeAllSeriesEvent -> handleSeeAllSeriesClick()
            HomeEvent.ClickSeeAllCharactersEvent -> handleSeeAllCharactersClick()
            else -> {}
        }
    }

    private fun handleCharacterClick(character: Character) {
        val direction =
            HomeFragmentDirections.actionHomeFragmentToDetailsCharacterFragment(character)
        binding.root.findNavController().navigate(direction)
    }

    private fun handleComicClick(comic: Comic) {
        val direction = HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(comic)
        binding.root.findNavController().navigate(direction)
    }

    private fun handleEventClick(event: Event) {
        val direction = HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(event)
        binding.root.findNavController().navigate(direction)
    }

    private fun handleSeriesClick(series: Series) {
        val direction = HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(series)
        binding.root.findNavController().navigate(direction)
    }

    private fun handleSeeAllComicsClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToComicsFragment()
        binding.root.findNavController().navigate(direction)
    }

    private fun handleSeeAllEventsClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToEventsFragment()
        binding.root.findNavController().navigate(direction)
    }

    private fun handleSeeAllSeriesClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToSeriesFragment()
        binding.root.findNavController().navigate(direction)
    }

    private fun handleSeeAllCharactersClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
        binding.root.findNavController().navigate(direction)
    }



}

