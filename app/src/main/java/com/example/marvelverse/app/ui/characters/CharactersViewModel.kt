package com.example.marvelverse.app.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository:MarvelRepository): BaseViewModel(), CharacterInteractionListener {

    private var _characters = MutableLiveData<DataState<Character>>()
    val characters: LiveData<DataState<Character>> get() = _characters

    private val _characterEvent = MutableLiveData<SingleEventState<CharactersEvent>>()
    val characterEvent: LiveData<SingleEventState<CharactersEvent>> get() = _characterEvent

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _characters.postValue(DataState.Loading)
        repository.searchCharacters().subscribeBy(::onCharactersSuccess, ::onCharactersError)
    }
    private fun onCharactersSuccess(it: List<Character>) {
        if (it.isEmpty()) {
            _characters.postValue(DataState.Empty)
        } else {
            _characters.postValue(DataState.Success(it))
        }
    }
    private fun onCharactersError(it: Throwable) {
        _characters.postValue(DataState.Error(it))
    }


    override fun onCharacterClick(character: Character) {
        _characterEvent.postValue(SingleEventState(CharactersEvent.ClickCharacterEvent(character)))
    }

}


