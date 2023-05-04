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
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


class ComicsFragment : BaseFragment<FragmentComicsBinding>(FragmentComicsBinding::inflate) ,
    ParentInteractionListener {
    private val viewModel: ComicsViewModel by viewModels()
    val comic = Comic(
        id = 51,
        title = "Sentry, the (Trade Paperback)",
        description = "On the edge of alcoholism and a failed marriage, Bob Reynolds wakes up to discover his true nature. A forgotten hero, he must unravel the conspiracy to erase his memory from mankind before an evil entity returns.",
        resourceURI = "http://gateway.marvel.com/v1/public/comics/1003",
        pageCount = 2561,
        series = RelatedSeries(resourceURI = "resource_u_r_i", available = 12),
        characters = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/comics/1158/characters", available = 650),
        creators = InfoWrapper(collectionURI = "collection_u_r_i", available = 4867),
        stories = InfoWrapper(collectionURI = "collection_u_r_i", available = 563),
        events = InfoWrapper(collectionURI = "collection_u_r_i", available = 3085),
        thumbnail = Thumbnail(path = "http://i.annihil.us/u/prod/marvel/i/mg/f/c0/4bc66d78f1bee", extension = "jpg")
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.titleRecycler.adapter= CharactersAdapter(listOf(),this)
        binding.title2Recycler.adapter= EventAdapter(listOf(),this)
        viewModel.getEvent(comic.events.collectionURI)
        viewModel.getCharacter(comic.characters.collectionURI)

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