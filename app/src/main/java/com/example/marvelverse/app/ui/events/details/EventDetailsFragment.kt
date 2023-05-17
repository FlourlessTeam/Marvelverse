package com.example.marvelverse.app.ui.events.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.app.ui.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentEventDetailsBinding
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Series
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment :
    InnerFragment<FragmentEventDetailsBinding>(FragmentEventDetailsBinding::inflate) {
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
            getCharacters(args.event.charactersUri!!)
            getSeries(args.event.seriesUri!!)
            getComics(args.event.comicsUri!!)
        }
    }

    private fun observeEvents() {

        viewModel.eventDetailsEvents.observe(viewLifecycleOwner) { clickEvent ->
            clickEvent.getUnHandledData()?.let {
                handleEvent(it)
            }
        }
    }
    fun handleEvent(event: EventDetailsEvents) {
        when (event) {
            is EventDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(event.character)
            is EventDetailsEvents.ClickComicEvent -> navigateToComicDetails(event.comic)
            is EventDetailsEvents.ClickSeriesEvent -> navigateToSeriesDetails(event.series)
        }
    }

    private fun navigateToCharacterDetails(character: Character) {
        val directions =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToDetailsCharacterFragment(
                character
            )
        goToNavigate(directions)
    }

    private fun navigateToComicDetails(comic: Comic) {
        val directions =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToComicDetailsFragment(
                comic
            )
        goToNavigate(directions)

    }

    private fun navigateToSeriesDetails(series: Series) {
        val directions =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToSeriesDetailsFragment(
                series
            )
        goToNavigate(directions)
    }
    private fun goToNavigate(direction: NavDirections){
        findNavController().navigate(direction)
    }
}