package com.example.marvelverse.app.ui.characters.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventDetailsAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentDetailsCharacterBinding
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series

class DetailsCharacterFragment :
    BaseFragment<FragmentDetailsCharacterBinding>(FragmentDetailsCharacterBinding::inflate) {
    private val viewModel: DetailsCharacterViewModel by viewModels()
    private val args: DetailsCharacterFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.item = args.character
        initRecyclerAdapters()
        getRecyclerData()
        observeEvents()
    }

    private fun initRecyclerAdapters() {
        binding.recyclerCharactersComics.adapter = ComicsAdapter(viewModel)
        binding.recyclerCharactersSeries.adapter = SeriesAdapter(viewModel)
        binding.recyclerCharactersEvents.adapter = EventDetailsAdapter(viewModel)
    }

    private fun getRecyclerData() {
        viewModel.apply {
            getEvent(args.character.events.collectionURI)
            getSeries(args.character.series.collectionURI)
            getComics(args.character.comics.collectionURI)
        }
    }

    private fun observeEvents() {
        viewModel.characterDetails.observe(viewLifecycleOwner) { clickEvent ->
            when (clickEvent) {
                is DetailsCharacterEvents.ClickEventEvent -> navigateToEventsDetails(clickEvent.event)
                is DetailsCharacterEvents.ClickComicEvent -> navigateToComicDetails(clickEvent.comic)
                is DetailsCharacterEvents.ClickSeriesEvent -> navigateToSeriesDetails(clickEvent.series)
                else -> {}
            }
            viewModel.clearEvents()
        }
    }

    private fun navigateToEventsDetails(event: Event) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToEventDetailsFragment(
                event
            )
        findNavController().navigate(direction)

    }

    private fun navigateToComicDetails(comic: Comic) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToComicDetailsFragment(
                comic
            )
        findNavController().navigate(direction)
    }

    private fun navigateToSeriesDetails(series: Series) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToSeriesDetailsFragment(
                series
            )
        findNavController().navigate(direction)
    }
}