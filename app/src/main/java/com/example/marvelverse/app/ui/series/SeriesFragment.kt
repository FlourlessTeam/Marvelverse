package com.example.marvelverse.app.ui.series

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.ParentInteractionListener
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentSeriesBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate),
    ParentInteractionListener {

    val series_x = Series(
        id = 31445,
        title = "Fantastic Four by Dan Slott Vol. 1 (2021)",
        description = "Norem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Norem ipsum dolor sit amet, consectetur adipiscing elit. ",
        resourceURI = "http://gateway.marvel.com/v1/public/series/31445",
        comics = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/series/18454/comics", available = 1796),
        characters = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/series/31445/characters", available = 259),
        creators = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/series/31445/creators", available = 4702),
        stories = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/series/31445/stories", available = 3492),
        events = InfoWrapper(collectionURI = "http://gateway.marvel.com/v1/public/series/31445/events", available = 845),
        thumbnail = Thumbnail(path = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available", extension = "jpg"))



    private val viewModel: SeriesViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner=this
        binding.viewModel= viewModel
        binding.item=series_x
        binding.seriesComics.adapter=ComicsAdapter(listOf(),this)
        binding.seriesEvents.adapter=EventAdapter(listOf(),this)
        binding.seriesCharacters.adapter=CharactersAdapter(listOf(),this)
        setup()
    }
    private fun setup() {
        viewModel.apply {
            getCharacters(series_x.characters.collectionURI)
            getEvent(series_x.events.collectionURI)
            getComics(series_x.comics.collectionURI)
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