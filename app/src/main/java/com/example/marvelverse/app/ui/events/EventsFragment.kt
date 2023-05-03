package com.example.marvelverse.app.ui.events

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.ParentInteractionListener
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentEventsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story


class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate),
	ParentInteractionListener {


	private val viewModel: EventViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		//binding.item = event
		binding.lifecycleOwner = this

		binding.characterAdapter = CharactersAdapter(listOf(), this)
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
		Toast.makeText(requireContext(), character.name, Toast.LENGTH_LONG).show()
	}

	override fun onEventClick(event: Event) {
		TODO("Not yet implemented")
	}

	override fun onComicClick(comic: Comic) {
		TODO("Not yet implemented")
	}

	override fun onStoriesClick(stories: Story) {
		TODO("Not yet implemented")
	}

	override fun onSeriesClick(series: Series) {
		TODO("Not yet implemented")
	}

	override fun onViewAllCharactersClick() {
		TODO("Not yet implemented")
	}

	override fun onViewAllEventsClick() {
		TODO("Not yet implemented")
	}

	override fun onViewAllComicsClick() {
		TODO("Not yet implemented")
	}

	override fun onViewAllStoriesClick() {
		TODO("Not yet implemented")
	}

	override fun onViewAllSeriesClick() {
		TODO("Not yet implemented")
	}

}