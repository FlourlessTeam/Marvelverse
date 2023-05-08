package com.example.marvelverse.app.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character


class CharactersViewModel : BaseViewModel(), CharacterInteractionListener {

    private var _characters = MutableLiveData<DataState<Character>>()
    val characters: LiveData<DataState<Character>> get() = _characters

    private val _characterEvent = MutableLiveData<CharactersEvent>()
    val characterEvent: LiveData<CharactersEvent> get() = _characterEvent

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _characters.postValue(DataState.Loading)
        MarvelRepository.searchCharacters().applySchedulers()
            .subscribe(
                {
                    _characters.postValue(DataState.Success(it))
                },
                {
                    _characters.postValue(DataState.Error(it))
                }
            ).addTo(disposables)
    }


    override fun onCharacterClick(character: Character) {
        _characterEvent.postValue(CharactersEvent.ClickCharacterEvent(character))
    }

    fun backToHome() {
        _characterEvent.postValue(CharactersEvent.BackToHome)
    }

    fun clearEvents() {
        if (_characterEvent.value != CharactersEvent.ReadyState)
            _characterEvent.postValue(CharactersEvent.ReadyState)
    }


}


