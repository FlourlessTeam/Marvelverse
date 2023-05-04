package com.example.marvelverse.app.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.app.ui.home.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.SeriesInteractionListener
import com.example.marvelverse.databinding.FragmentEventsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series


class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate),
	CharacterInteractionListener, SeriesInteractionListener, ComicInteractionListener {


	private val viewModel: EventViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		//binding.item = event
		binding.lifecycleOwner = this

		binding.eventCharacters.adapter = CharactersAdapter(listOf(), this)
		binding.eventSeries.adapter = SeriesAdapter(listOf(), this)
		binding.eventComics.adapter = ComicsAdapter(listOf(), this)

		setup()
	}

	private fun setup() {
		viewModel.apply {
			//getCharacters(event.characters.collectionURI)
			//getSeries(event.series.resourceURI)
			//getComics(event.comics.collectionURI)
		}
	}

	override fun onCharacterClick(character: Character) {
	}

	override fun onComicClick(comic: Comic) {
	}

	override fun onSeriesClick(series: Series) {
	}
}