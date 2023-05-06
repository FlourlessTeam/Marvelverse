package com.example.marvelverse.app.ui.events.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentEventDetailsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series


class EventDetailsFragment :
    BaseFragment<FragmentEventDetailsBinding>(FragmentEventDetailsBinding::inflate) {
    private val args: EventDetailsFragmentArgs by navArgs()
    private val viewModel: EventDetailsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.item = args.event
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerAdapters()
        getRecyclerData()
        observeEvents()
    }

    private fun initRecyclerAdapters() {
        binding.eventCharacters.adapter = CharactersAdapter(viewModel)
        binding.eventSeries.adapter = SeriesAdapter(viewModel)
        binding.eventComics.adapter = ComicsAdapter(viewModel)
    }

    private fun getRecyclerData() {
        viewModel.apply {
            getCharacters(args.event.characters.collectionURI)
            getSeries(args.event.series.collectionURI)
            getComics(args.event.comics.collectionURI)
        }
    }

    private fun observeEvents() {
        viewModel.eventDetailsEvents.observe(viewLifecycleOwner, Observer { clickEvent ->
            when (clickEvent) {
                is EventDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(clickEvent.character)
                is EventDetailsEvents.ClickComicEvent -> navigateToComicDetails(clickEvent.comic)
                is EventDetailsEvents.ClickSeriesEvent -> navigateToSeriesDetails(clickEvent.series)
                else -> {}
            }
        })
    }

    private fun navigateToCharacterDetails(character: Character) {
    }

    private fun navigateToComicDetails(comic: Comic) {

    }

    private fun navigateToSeriesDetails(series: Series) {
    }
}