package com.example.marvelverse.app.ui.comics.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.utilites.SingleEventState


class ComicsDetailsViewModel : BaseViewModel(), CharacterInteractionListener,
    EventInteractionListener {
    private var _character = MutableLiveData<DataState<Character>>()
    private var _event = MutableLiveData<DataState<Event>>()
    private var _comicsDetailsEvent = MutableLiveData<SingleEventState<ComicDetailsEvents>>()
    val character: LiveData<DataState<Character>> get() = _character
    val event: LiveData<DataState<Event>> get() = _event
    val comicsDetailsEvent: LiveData<SingleEventState<ComicDetailsEvents>> get() = _comicsDetailsEvent
    fun getCharacter(url: String) {
        MarvelRepository.getCharactersByUrl(url).subscribeBy(::onCharacterSuccess, ::onCharacterError)
    }
    fun onCharacterSuccess(it: List<Character>) {
        if (it.isEmpty()) {
            _character.postValue(DataState.Empty)
        } else {
            _character.postValue(DataState.Success(it))
        }
    }
    fun onCharacterError(it: Throwable) {
        _character.postValue(DataState.Error(it))
    }

    fun getEvent(url: String) {
        MarvelRepository.getEventsByUrl(url).subscribeBy(::onEventSuccess, ::onEventError)
    }
    fun onEventSuccess(it: List<Event>) {
        if (it.isEmpty()) {
            _event.postValue(DataState.Empty)
        } else {
            _event.postValue(DataState.Success(it))
        }
    }
    fun onEventError(it: Throwable) {
        _event.postValue(DataState.Error(it))
    }


    override fun onCharacterClick(character: Character) {
        _comicsDetailsEvent.postValue(SingleEventState(ComicDetailsEvents.ClickCharacterEvent(character)))

    }

    override fun onEventClick(event: Event) {
        _comicsDetailsEvent.postValue(SingleEventState(ComicDetailsEvents.ClickEventEvent(event)))
    }

}