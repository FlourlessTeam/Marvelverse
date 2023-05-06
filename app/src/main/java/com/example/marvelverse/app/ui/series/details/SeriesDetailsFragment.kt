package com.example.marvelverse.app.ui.series.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentSeriesDetailsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event

class SeriesDetailsFragment :
    BaseFragment<FragmentSeriesDetailsBinding>(FragmentSeriesDetailsBinding::inflate) {
    private val viewModel: SeriesDetailsViewModel by viewModels()
    private val args:SeriesDetailsFragmentArgs by navArgs()

    @SuppressLint("SuspiciousIndentation")
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
        binding.seriesEvents.adapter = EventAdapter(viewModel)
        binding.seriesComics.adapter = ComicsAdapter(viewModel)
    }

    private fun getRecyclerData() {
        viewModel.apply {
            getCharacters(args.series .characters.collectionURI)
//            getEvent(args.series.events.collectionURI)
            getComics(args.series.comics.collectionURI)
        }
    }


    private fun observeSeries() {
        viewModel.detailsSeries.observe(viewLifecycleOwner, Observer { clickSeries ->
            when (clickSeries) {
                is DetailsSeries.ClickCharacterSeries -> navigateToCharacterDetails(clickSeries.character)
                is DetailsSeries.ClickComicSeries -> navigateToComicDetails(clickSeries.comic)
                is DetailsSeries.ClickEventSeries -> navigateToEventDetails(clickSeries.event)
            }
        })
    }

    private fun navigateToCharacterDetails(character: Character) {

    }

    private fun navigateToComicDetails(comic: Comic) {

    }

    private fun navigateToEventDetails(event: Event) {

    }

}