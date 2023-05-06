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
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


class EventDetailsFragment :
	BaseFragment<FragmentEventDetailsBinding>(FragmentEventDetailsBinding::inflate) {
	val event = Event(
		id = 116,
		title = "Acts of Vengeance!",
		description = "Loki sets about convincing the super-villains of Earth to attack heroes other than those they normally fight in an attempt to destroy the Avengers to absolve his guilt over inadvertently creating the team in the first place.",
		series = RelatedSeries(
			resourceURI = "http://gateway.marvel.com/v1/public/events/116/series",
			available = 4201
		),
		comics = InfoWrapper(
			collectionURI = "http://gateway.marvel.com/v1/public/events/116/comics",
			available = 4099
		),
		creators = InfoWrapper(collectionURI = "collection_u_r_i", available = 2295),
		stories = InfoWrapper(collectionURI = "collection_u_r_i", available = 3334),
		characters = InfoWrapper(
			collectionURI = "http://gateway.marvel.com/v1/public/events/116/characters",
			available = 4477
		),
		thumbnail = Thumbnail(
			path = "http://i.annihil.us/u/prod/marvel/i/mg/9/40/51ca10d996b8b",
			extension = "jpg"
		)
	)


	private val viewModel: EventDetailsViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		binding.item = event
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
			getCharacters(event.characters.collectionURI)
			getSeries(event.series.resourceURI)
			getComics(event.comics.collectionURI)
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