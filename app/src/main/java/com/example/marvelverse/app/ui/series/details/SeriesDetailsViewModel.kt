package com.example.marvelverse.app.ui.series.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), ComicInteractionListener,
    EventInteractionListener,
    CharacterInteractionListener {


    private val _characters: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val _detailsSeries: MutableLiveData<SingleEventState<SeriesDetailsEvents>> = MutableLiveData()

    val characters: LiveData<DataState<Character>>
        get() = _characters
    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val events: LiveData<DataState<Event>>
        get() = _events
    val detailsSeries: LiveData<SingleEventState<SeriesDetailsEvents>>
        get() = _detailsSeries

    fun getCharacters(url: String) {
        repository.getCharactersByUrl(url)
            .subscribeBy(::onCharacterSuccess, ::onCharacterError)
    }

    private fun onCharacterSuccess(character: List<Character>) {
        _characters.postValue(DataState.Success(character))
    }

    private fun onCharacterError(error: Throwable) {
        _characters.postValue(DataState.Error(error))
    }

    fun getComics(url: String) {
        repository.getComicsByUrl(url).subscribeBy(::onComicSuccess, ::onComicError)
    }

    private fun onComicSuccess(comic: List<Comic>) {
        if (comic.isEmpty()) {
            _comics.postValue(DataState.Empty)
        } else {
            _comics.postValue(DataState.Success(comic))
        }
    }

    private fun onComicError(error: Throwable) {
        _comics.postValue(DataState.Error(error))
    }

    fun getEvents(url: String) {
        repository.getEventsByUrl(url).subscribeBy(::onEventSuccess, ::onEventError)
    }

    private fun onEventSuccess(event: List<Event>) {
        if (event.isEmpty()) {
            _events.postValue(DataState.Empty)
        } else {
            _events.postValue(DataState.Success(event))
        }
    }

    private fun onEventError(error: Throwable) {
        _events.postValue(DataState.Error(error))
    }

    override fun onCharacterClick(character: Character) {
        _detailsSeries.postValue(SingleEventState(SeriesDetailsEvents.ClickCharacterSeries(character)))
    }

    override fun onComicClick(comic: Comic) {
        _detailsSeries.postValue(SingleEventState(SeriesDetailsEvents.ClickComicSeries(comic)))
    }

    override fun onEventClick(event: Event) {
        _detailsSeries.postValue(SingleEventState(SeriesDetailsEvents.ClickEventSeries(event)))
    }
}