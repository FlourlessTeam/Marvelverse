package com.example.marvelverse.app.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "CharactersViewModel"

class CharactersViewModel : ViewModel(), CharactersAdapter.OnCharacterClickListener {
    private val compositeDisposable = CompositeDisposable()

    private var _characters = MutableLiveData<DataState<List<Character>>>()
    val characters: LiveData<DataState<List<Character>>> get() = _characters

    init {
        getCharacters()
    }

    private fun getCharacters() {
        _characters.postValue(DataState.Loading)
        MarvelRepository.searchCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _characters.postValue(DataState.Success(it))
                },
                onError = {
                    _characters.postValue(DataState.Error(it))
                }
            ).addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onClick(character: Character) {
        Log.d(TAG, "onClick: ${character.name}")
    }
}


