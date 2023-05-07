package com.example.marvelverse.app.ui.comics.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.abstracts.InnerFragment
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.EventDetailsAdapter
import com.example.marvelverse.databinding.FragmentComicDetailsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Event

class ComicDetailsFragment :
    InnerFragment<FragmentComicDetailsBinding>(FragmentComicDetailsBinding::inflate) {
    private val viewModel: ComicsDetailsViewModel by viewModels()
    private val args: ComicDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.item = args.comic
        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerAdapters()
        getRecyclerData()
        observeEvents()
    }

    private fun initRecyclerAdapters() {
        binding.characterRecycler.adapter = CharactersAdapter(viewModel)
        binding.eventRecycler.adapter = EventDetailsAdapter(viewModel)
    }

    private fun getRecyclerData() {
        viewModel.getEvent(args.comic.events.collectionURI)
        viewModel.getCharacter(args.comic.characters.collectionURI)
    }

    private fun observeEvents() {
        viewModel.comicsDetailsEvent.observe(viewLifecycleOwner) {
            when (it) {
                is ComicDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(it.character)
                is ComicDetailsEvents.ClickEventEvent -> navigateToEventDetails(it.event)
                else -> {}
            }
            viewModel.clearEvents()
        }
    }


    private fun navigateToCharacterDetails(character: Character) {

        val directions =
            ComicDetailsFragmentDirections.actionComicDetailsFragmentToDetailsCharacterFragment(
                character
            )
        findNavController().navigate(directions)
    }

    private fun navigateToEventDetails(event: Event) {
        val directions =
            ComicDetailsFragmentDirections.actionComicDetailsFragmentToEventDetailsFragment(
                event
            )
        findNavController().navigate(directions)
    }

}