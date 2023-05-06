package com.example.marvelverse.app.ui.events.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

	private val viewModel: EventDetailsViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		//binding.item = event
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
			//getCharacters(event.characters.collectionURI)
			//getSeries(event.series.resourceURI)
			//getComics(event.comics.collectionURI)
		}
	}

	private fun observeEvents() {
		viewModel.eventDetailsEvents.observe(viewLifecycleOwner, Observer { clickEvent ->
			when (clickEvent) {
				is EventDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(clickEvent.character)
				is EventDetailsEvents.ClickComicEvent -> navigateToComicDetails(clickEvent.comic)
				is EventDetailsEvents.ClickSeriesEvent -> navigateToSeriesDetails(clickEvent.series)
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