package com.example.marvelverse.app.ui.characters.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.app.ui.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.adapter.EventDetailsAdapter
import com.example.marvelverse.app.ui.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentDetailsCharacterBinding
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series

class DetailsCharacterFragment :
    InnerFragment<FragmentDetailsCharacterBinding>(FragmentDetailsCharacterBinding::inflate) {
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
            getEvent(args.character.eventsUri.collectionURI)
            getSeries(args.character.seriesUri.collectionURI)
            getComics(args.character.comicsUri.collectionURI)
        }
    }

    private fun observeEvents() {
        viewModel.characterDetails.observe(viewLifecycleOwner) { clickEvent ->
            clickEvent.getUnHandledData()?.let {
                handleEvent(it)
            }
        }
    }
    fun handleEvent(event: DetailsCharacterEvents) {
        when (event) {
            is DetailsCharacterEvents.ClickComicEvent -> navigateToComicDetails(event.comic)
            is DetailsCharacterEvents.ClickEventEvent -> navigateToEventsDetails(event.event)
            is DetailsCharacterEvents.ClickSeriesEvent -> navigateToSeriesDetails(event.series)
        }
    }

    private fun navigateToEventsDetails(event: Event) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToEventDetailsFragment(
                event
            )
        goToNavigate(direction)

    }

    private fun navigateToComicDetails(comic: Comic) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToComicDetailsFragment(
                comic
            )
        goToNavigate(direction)
    }

    private fun navigateToSeriesDetails(series: Series) {
        val direction =
            DetailsCharacterFragmentDirections.actionDetailsCharacterFragmentToSeriesDetailsFragment(
                series
            )
       goToNavigate(direction)
    }
    private fun goToNavigate(direction: NavDirections){
        binding.root.findNavController().navigate(direction)
    }
}