package com.example.marvelverse.app.ui.series.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.app.ui.home.ParentInteractionListener
import com.example.marvelverse.app.ui.home.adapter.CharactersAdapter
import com.example.marvelverse.app.ui.home.adapter.ComicsAdapter
import com.example.marvelverse.app.ui.home.adapter.EventAdapter
import com.example.marvelverse.databinding.FragmentSeriesBinding
import com.example.marvelverse.databinding.FragmentSeriesDetailsBinding
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>(FragmentSeriesDetailsBinding::inflate){

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
        thumbnail = Thumbnail(path = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available", extension = "jpg")
    )



    private val viewModel: SeriesViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner=this
        binding.viewModel= viewModel
        binding.item=series_x
//        binding.seriesComics.adapter= ComicsAdapter(listOf(),this)
//        binding.seriesEvents.adapter= EventAdapter(listOf(),this)
//        binding.seriesCharacters.adapter= CharactersAdapter(listOf(),this)
        setup()
    }
    private fun setup() {
        viewModel.apply {
            getCharacters(series_x.characters.collectionURI)
            getEvent(series_x.events.collectionURI)
            getComics(series_x.comics.collectionURI)
        }
    }

}