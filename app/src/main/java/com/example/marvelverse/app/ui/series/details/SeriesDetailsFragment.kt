package com.example.marvelverse.app.ui.series.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.events.details.DetailsEvent
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentSeriesDetailsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>(FragmentSeriesDetailsBinding::inflate){
    private val viewModel: SeriesDetailsViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner=this
        binding.viewModel= viewModel
        initRecyclerAdapters()
        getRecyclerData()
        observeSeries()
    }


    private fun initRecyclerAdapters() {
        binding.seriesCharacters.adapter = CharactersAdapter(listOf(), viewModel)
        binding.seriesEvents.adapter = EventAdapter(listOf(), viewModel)
        binding.seriesComics.adapter = ComicsAdapter(listOf(), viewModel)
    }
    private fun getRecyclerData() {
        viewModel.apply {
//            getCharacters(series_x.characters.collectionURI)
//            getEvent(series_x.events.collectionURI)
//            getComics(series_x.comics.collectionURI)
        }
    }



    private fun observeSeries() {
        viewModel.detailsSeries.observe(viewLifecycleOwner, Observer { clickSeries ->
            when (clickSeries) {
                is DetailsSeries.ClickCharacterSeries -> handleCharacterClick(clickSeries.character)
                is DetailsSeries.ClickComicSeries -> handleComicClick(clickSeries.comic)
                is DetailsSeries.ClickEventSeries -> handleEventClick(clickSeries.event)
            }
        })
    }

    private fun handleCharacterClick(character: Character) {

    }

    private fun handleComicClick(comic: Comic) {

    }

    private fun handleEventClick(event: Event) {

    }

}