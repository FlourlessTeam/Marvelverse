package com.example.marvelverse.app.ui.series.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.app.ui.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.adapter.EventDetailsAdapter
import com.example.marvelverse.databinding.FragmentSeriesDetailsBinding
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event

class SeriesDetailsFragment :
    InnerFragment<FragmentSeriesDetailsBinding>(FragmentSeriesDetailsBinding::inflate) {
    private val viewModel: SeriesDetailsViewModel by viewModels()
    private val args: SeriesDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.item = args.series
        initRecyclerAdapters()
        getRecyclerData()
        observeSeries()
    }


    private fun initRecyclerAdapters() {
        binding.seriesCharacters.adapter = CharactersAdapter(viewModel)
        binding.seriesEvents.adapter = EventDetailsAdapter(viewModel)
        binding.seriesComics.adapter = ComicsAdapter(viewModel)
    }

    private fun getRecyclerData() {
        viewModel.apply {
            getCharacters(args.series.charactersUri!!)
            getEvents(args.series.eventsUri!!)
            getComics(args.series.comicsUri!!)
        }
    }


    private fun observeSeries() {
        viewModel.detailsSeries.observe(viewLifecycleOwner) { event ->
            event.getUnHandledData()?.let {
                handleEvent(it)
            }
        }

    }

    fun handleEvent(event: DetailsSeries) {
        when (event) {
            is DetailsSeries.ClickCharacterSeries -> navigateToCharacterDetails(event.character)
            is DetailsSeries.ClickComicSeries -> navigateToComicDetails(event.comic)
            is DetailsSeries.ClickEventSeries -> navigateToEventDetails(event.event)
        }
    }

    private fun navigateToCharacterDetails(character: Character) {
        val directions =
            SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToDetailsCharacterFragment(
                character
            )
        findNavController().navigate(directions)
    }

    private fun navigateToComicDetails(comic: Comic) {
        val directions =
            SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToComicDetailsFragment(
                comic
            )
        findNavController().navigate(directions)
    }

    private fun navigateToEventDetails(event: Event) {
        val directions =
            SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToEventDetailsFragment(
                event
            )
        findNavController().navigate(directions)

    }

}