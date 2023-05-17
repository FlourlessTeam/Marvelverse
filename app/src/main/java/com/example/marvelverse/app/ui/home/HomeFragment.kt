package com.example.marvelverse.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marvelverse.app.ui.base.BottomNavFragment
import com.example.marvelverse.app.ui.adapter.HomeAdapter
import com.example.marvelverse.data.dataSources.local.MarvelDatabase
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BottomNavFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter(viewModel) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.homeEvent.observe(viewLifecycleOwner) { event ->
            event.getUnHandledData()?.let {
                handleEvent(it)
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
        }
    }

    private fun handleCharacterClick(character: Character) {
        val direction =
            HomeFragmentDirections.actionHomeFragmentToDetailsCharacterFragment(character)
        goToNavigate(direction)
    }

    private fun handleComicClick(comic: Comic) {
        val direction = HomeFragmentDirections.actionHomeFragmentToComicDetailsFragment(comic)
        goToNavigate(direction)
    }

    private fun handleEventClick(event: Event) {
        val direction = HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(event)
        goToNavigate(direction)
    }

    private fun handleSeriesClick(series: Series) {
        val direction = HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(series)
        goToNavigate(direction)
    }

    private fun handleSeeAllComicsClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToComicsFragment()
        goToNavigate(direction)
    }

    private fun handleSeeAllEventsClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToEventsFragment()
        goToNavigate(direction)
    }

    private fun handleSeeAllSeriesClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToSeriesFragment()
        goToNavigate(direction)
    }

    private fun handleSeeAllCharactersClick() {
        val direction = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
        goToNavigate(direction)
    }
    private fun goToNavigate(direction: NavDirections){
        binding.root.findNavController().navigate(direction)
    }


}




