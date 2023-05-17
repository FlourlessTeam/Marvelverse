package com.example.marvelverse.app.ui.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.utilites.SingleEventState

class EventDetailsViewModel : BaseViewModel(), CharacterInteractionListener,
    ComicInteractionListener,
    SeriesInteractionListener {
    private val repositry = MarvelRepository()


    private val _character: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _series: MutableLiveData<DataState<Series>> = MutableLiveData()
    private val _eventDetailsEvents: MutableLiveData<SingleEventState<EventDetailsEvents>> =
        MutableLiveData()

    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val series: LiveData<DataState<Series>>
        get() = _series
    val character: LiveData<DataState<Character>>
        get() = _character

    val eventDetailsEvents: LiveData<SingleEventState<EventDetailsEvents>>
        get() = _eventDetailsEvents


    fun getCharacters(url: String) {
        _character.postValue(DataState.Loading)
        repositry.getCharactersByUrl(url)
            .subscribeBy(::OnSuccessCharacter, ::OnErrorCharacter)
    }

    fun OnSuccessCharacter(it: List<Character>) {
        if (it.isEmpty()) {
            _character.postValue(DataState.Empty)
        } else {
            _character.postValue(DataState.Success(it))
        }
    }

    fun OnErrorCharacter(it: Throwable) {
        _character.postValue(DataState.Error(it))
    }

    fun getComics(url: String) {
        _comics.postValue(DataState.Loading)
        repositry.getComicsByUrl(url).subscribeBy(::OnSuccessComic, ::OnErrorComic)
    }

    fun OnSuccessComic(it: List<Comic>) {
        if (it.isEmpty()) {
            _comics.postValue(DataState.Empty)
        } else {
            _comics.postValue(DataState.Success(it))
        }
    }

    fun OnErrorComic(it: Throwable) {
        _comics.postValue(DataState.Error(it))
    }

    fun getSeries(url: String) {
        _series.postValue(DataState.Loading)
        repositry.getSeriesByUrl(url).subscribeBy(::OnSuccessSeries, ::OnErrorSeries)
    }

    fun OnSuccessSeries(it: List<Series>) {
        if (it.isEmpty()) {
            _series.postValue(DataState.Empty)
        } else {
            _series.postValue(DataState.Success(it))
        }
    }

    fun OnErrorSeries(it: Throwable) {
        _series.postValue(DataState.Error(it))
    }

    override fun onCharacterClick(character: Character) {
        _eventDetailsEvents.postValue(
            SingleEventState(
                EventDetailsEvents.ClickCharacterEvent(
                    character
                )
            )
        )
    }

    override fun onComicClick(comic: Comic) {
        _eventDetailsEvents.postValue(SingleEventState(EventDetailsEvents.ClickComicEvent(comic)))
    }

    override fun onSeriesClick(series: Series) {
        _eventDetailsEvents.postValue(SingleEventState(EventDetailsEvents.ClickSeriesEvent(series)))
    }

}