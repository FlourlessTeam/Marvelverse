package com.example.marvelverse.app.ui.comics.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsDetailsViewModel @Inject constructor(private val repository: MarvelRepository) :
    BaseViewModel(), CharacterInteractionListener,
    EventInteractionListener {

    private var _character = MutableLiveData<DataState<Character>>()
    private var _event = MutableLiveData<DataState<Event>>()
    private var _comicsDetailsEvent = MutableLiveData<SingleEventState<ComicDetailsEvents>>()
    val character: LiveData<DataState<Character>> get() = _character
    val event: LiveData<DataState<Event>> get() = _event
    val comicsDetailsEvent: LiveData<SingleEventState<ComicDetailsEvents>> get() = _comicsDetailsEvent
    fun getCharacter(url: String) {
        repository.getCharactersByUrl(url).subscribeBy(::onCharacterSuccess, ::onCharacterError)
    }

    private fun onCharacterSuccess(it: List<Character>) {
        if (it.isEmpty()) {
            _character.postValue(DataState.Empty)
        } else {
            _character.postValue(DataState.Success(it))
        }
    }

    private fun onCharacterError(it: Throwable) {
        _character.postValue(DataState.Error(it))
    }

    fun getEvent(url: String) {
        repository.getEventsByUrl(url).subscribeBy(::onEventSuccess, ::onEventError)
    }

    private fun onEventSuccess(it: List<Event>) {
        if (it.isEmpty()) {
            _event.postValue(DataState.Empty)
        } else {
            _event.postValue(DataState.Success(it))
        }
    }

    private fun onEventError(it: Throwable) {
        _event.postValue(DataState.Error(it))
    }


    override fun onCharacterClick(character: Character) {
        _comicsDetailsEvent.postValue(
            SingleEventState(
                ComicDetailsEvents.ClickCharacterEvent(
                    character
                )
            )
        )

    }

    override fun onEventClick(event: Event) {
        _comicsDetailsEvent.postValue(SingleEventState(ComicDetailsEvents.ClickEventEvent(event)))
    }

}