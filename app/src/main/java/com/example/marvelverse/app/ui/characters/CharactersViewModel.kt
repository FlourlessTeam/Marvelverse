package com.example.marvelverse.app.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.home.interfaces.CharacterInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class CharactersViewModel : ViewModel(), CharacterInteractionListener {
    private val compositeDisposable = CompositeDisposable()

    private var _characters = MutableLiveData<DataState<Character>>()
    val characters: LiveData<DataState<Character>> get() = _characters

    private val _characterEvent = MutableLiveData<CharactersEvent>()
    val characterEvent: LiveData<CharactersEvent> get() = _characterEvent

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _characters.postValue(DataState.Loading)
        MarvelRepository.searchCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _characters.postValue(DataState.Success(it))
                },
                {
                    _characters.postValue(DataState.Error(it))
                }
            ).addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}


