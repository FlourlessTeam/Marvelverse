package com.example.marvelverse.app.ui.comics.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.app.ui.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.adapter.EventDetailsAdapter
import com.example.marvelverse.databinding.FragmentComicDetailsBinding
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Event

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
        viewModel.getEvent(args.comic.eventsUri!!)
        viewModel.getCharacter(args.comic.charactersUri!!)
    }

    private fun observeEvents() {
        viewModel.comicsDetailsEvent.observe(viewLifecycleOwner) { event->
            event.getUnHandledData()?.let {
                handleEvent(it)
            }
        }
    }
    fun handleEvent(event: ComicDetailsEvents) {
        when (event) {
            is ComicDetailsEvents.ClickCharacterEvent -> navigateToCharacterDetails(event.character)
            is ComicDetailsEvents.ClickEventEvent -> navigateToEventDetails(event.event)
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