package com.example.marvelverse.app.ui.series.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.utilites.SingleEventState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel() : BaseViewModel(), ComicInteractionListener,
    EventInteractionListener,
    CharacterInteractionListener {

    private val _characters: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val _detailsSeries: MutableLiveData<SingleEventState<DetailsSeries>> = MutableLiveData()

    val characters: LiveData<DataState<Character>>
        get() = _characters
    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val events: LiveData<DataState<Event>>
        get() = _events
    val detailsSeries: LiveData<SingleEventState<DetailsSeries>>
        get() = _detailsSeries

    fun getCharacters(url: String) {
        MarvelRepository.getCharactersByUrl(url)
            .subscribeBy(::OnCharacterSuccess, ::OnCharacterError)
    }

    fun OnCharacterSuccess(character: List<Character>) {
        _characters.postValue(DataState.Success(character))
    }

    fun OnCharacterError(error: Throwable) {
        _characters.postValue(DataState.Error(error))
    }

    fun getComics(url: String) {
        MarvelRepository.getComicsByUrl(url).subscribeBy(::OnComicSuccess, ::OnComicError)
    }

    fun OnComicSuccess(comic: List<Comic>) {
        if (comic.isEmpty()) {
            _comics.postValue(DataState.Empty)
        } else {
            _comics.postValue(DataState.Success(comic))
        }
    }

    fun OnComicError(error: Throwable) {
        _comics.postValue(DataState.Error(error))
    }

    fun getEvents(url: String) {
        MarvelRepository.getEventsByUrl(url).subscribeBy(::OnEventSuccess, ::OnEventError)
    }
    fun OnEventSuccess(event: List<Event>) {
        if (event.isEmpty()) {
            _events.postValue(DataState.Empty)
        } else {
            _events.postValue(DataState.Success(event))
        }
    }
    fun OnEventError(error: Throwable) {
        _events.postValue(DataState.Error(error))
    }

    override fun onCharacterClick(character: Character) {
        _detailsSeries.postValue(SingleEventState(DetailsSeries.ClickCharacterSeries(character)))
    }

    override fun onComicClick(comic: Comic) {
        _detailsSeries.postValue(SingleEventState(DetailsSeries.ClickComicSeries(comic)))
    }

    override fun onEventClick(event: Event) {
        _detailsSeries.postValue(SingleEventState(DetailsSeries.ClickEventSeries(event)))
    }
}