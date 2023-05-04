package com.example.marvelverse.app.ui.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.ParentInteractionListener
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentComicsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story


class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) ,
    ParentInteractionListener {
    private val viewModel: ComicsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.titleRecycler.adapter= CharactersAdapter(listOf(),this)
        binding.title2Recycler.adapter= EventAdapter(listOf(),this)
    }

    override fun onCharacterClick(character: Character) {
        TODO("Not yet implemented")
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