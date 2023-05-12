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


class ComicsDetailsViewModel : BaseViewModel(), CharacterInteractionListener,
    EventInteractionListener {
    private var _character = MutableLiveData<DataState<Character>>()
    private var _event = MutableLiveData<DataState<Event>>()
    private var _comicsDetailsEvent = MutableLiveData<ComicDetailsEvents>()
    val character: LiveData<DataState<Character>> get() = _character
    val event: LiveData<DataState<Event>> get() = _event
    val comicsDetailsEvent: LiveData<ComicDetailsEvents> get() = _comicsDetailsEvent
    fun getCharacter(url: String) {
        MarvelRepository.getCharactersByUrl(url)
            .applySchedulers()
            .subscribe(
                {
                    _character.postValue(DataState.Success(it))
                },
                {
                    _character.postValue(DataState.Error(it))
                }).addTo(disposables)
    }

    fun getEvent(url: String) {
        MarvelRepository.getEventsByUrl(url)
            .applySchedulers()
            .subscribe(
                {
                    _event.postValue(DataState.Success(it))
                },
                {
                    _event.postValue(DataState.Error(it))
                }).addTo(disposables)

    }


    override fun onCharacterClick(character: Character) {
        _comicsDetailsEvent.postValue(ComicDetailsEvents.ClickCharacterEvent(character))

    }

    override fun onEventClick(event: Event) {
        _comicsDetailsEvent.postValue(ComicDetailsEvents.ClickEventEvent(event))
    }

    fun clearEvents() {
        if (_comicsDetailsEvent.value != ComicDetailsEvents.ReadyState)
            _comicsDetailsEvent.postValue(ComicDetailsEvents.ReadyState)
    }
}