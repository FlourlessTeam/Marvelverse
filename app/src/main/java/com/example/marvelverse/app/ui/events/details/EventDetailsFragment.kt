package com.example.marvelverse.app.ui.events.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.SeriesAdapter
import com.example.marvelverse.databinding.FragmentEventDetailsBinding


class EventDetailsFragment :
	BaseFragment<FragmentEventDetailsBinding>(FragmentEventDetailsBinding::inflate){

	private val viewModel: EventDetailsViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		//binding.item = event
		binding.lifecycleOwner = viewLifecycleOwner

		binding.eventCharacters.adapter = CharactersAdapter(listOf(), viewModel)
		binding.eventSeries.adapter = SeriesAdapter(listOf(), viewModel)
		binding.eventComics.adapter = ComicsAdapter(listOf(), viewModel)

		setup()
	}

	private fun setup() {
		viewModel.apply {
			//getCharacters(event.characters.collectionURI)
			//getSeries(event.series.resourceURI)
			//getComics(event.comics.collectionURI)
		}
	}
}