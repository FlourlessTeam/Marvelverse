package com.example.marvelverse.app.ui.comics.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentComicDetailsBinding
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Character
class ComicDetailsFragment :
    BaseFragment<FragmentComicDetailsBinding>(FragmentComicDetailsBinding::inflate) {
    private val viewModel: ComicsDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.titleRecycler.adapter = CharactersAdapter(viewModel)
        binding.title2Recycler.adapter = EventAdapter(viewModel)
        viewModel.getEvent(comic.events.collectionURI)
        viewModel.getCharacter(comic.characters.collectionURI)
        viewModel.comicsDetailsEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ComicDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(it.character)
                is ComicDetailsEvents.ClickEventEvent -> navigateToEventDetails(it.event)

            }


        })


    }

    private fun navigateToCharacterDetails(character: Character) {

    }

    private fun navigateToEventDetails(event: Event) {

    }

}