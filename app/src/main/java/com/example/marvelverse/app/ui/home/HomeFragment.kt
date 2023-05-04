package com.example.marvelverse.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.HomeAdapter
import com.example.marvelverse.databinding.FragmentHomeBinding
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
        viewModel.hoveEvents.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                handleEvent(event)
            }
        })
    }

    private fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ClickCharacterEvent -> handleCharacterClick(event.character)
            is HomeEvent.ClickComicEvent -> handleComicClick(event.comic)
            is HomeEvent.ClickEventEvent -> handleEventClick(event.event)
            is HomeEvent.ClickSeriesEvent -> handleSeriesClick(event.series)
            is HomeEvent.ClickStoryEvent -> handleStoryClick(event.story)
            HomeEvent.ClickSeeAllComicsEvent -> handleSeeAllComicsClick()
            HomeEvent.ClickSeeAllEventsEvent -> handleSeeAllEventsClick()
            HomeEvent.ClickSeeAllSeriesEvent -> handleSeeAllSeriesClick()
            HomeEvent.ClickSeeAllStoriesEvent -> handleSeeAllStoriesClick()
            HomeEvent.ClickSeeAllCharactersEvent -> handleSeeAllCharactersClick()
        }
    }

    private fun handleStoryClick(story: Story) {
        Log.d("HomeFragment", "ClickStoryEvent $story")

    }

    private fun handleCharacterClick(character: Character) {
        Log.d("HomeFragment", "ClickCharacterEvent $character")
    }

    private fun handleComicClick(comic: Comic) {
        Log.d("HomeFragment", "ClickComicEvent $comic")
    }

    private fun handleEventClick(event: Event) {
        Log.d("HomeFragment", "ClickEventEvent $event")
    }
    private fun handleSeriesClick(series: Series) {
        Log.d("HomeFragment", "ClickSeriesEvent $series")
    }

    private fun handleSeeAllComicsClick() {
        Log.d("HomeFragment", "ClickSeeAllComicsEvent")
    }

    private fun handleSeeAllEventsClick() {
        Log.d("HomeFragment", "ClickSeeAllEventsEvent")
    }

    private fun handleSeeAllSeriesClick() {
        Log.d("HomeFragment", "ClickSeeAllSeriesEvent")
    }

    private fun handleSeeAllStoriesClick() {
        Log.d("HomeFragment", "ClickSeeAllStoriesEvent")
    }

    private fun han() {
        Log.d("HomeFragment", "ClickSeriesEvent")
    }

    private fun handleSeeAllCharactersClick() {
        Log.d("HomeFragment", "ClickSeeAllCharactersEvent")
    }
}

