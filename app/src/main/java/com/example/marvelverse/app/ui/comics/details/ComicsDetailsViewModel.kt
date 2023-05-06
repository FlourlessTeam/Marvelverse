package com.example.marvelverse.app.ui.comics.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ComicsDetailsViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private var _character = MutableLiveData<DataState<Character>>()
    private var _event= MutableLiveData<DataState<Event>>()
    val character: LiveData<DataState<Character>> get() = _character
    val event: LiveData<DataState<Event>> get() =_event
    fun getCharacter(url:String) {
        _character.postValue(DataState.Loading)
        MarvelRepository.getCharactersByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _character.postValue(DataState.Success(it))
                },
                {
                    _character.postValue(DataState.Error(it))
                }).addTo(compositeDisposable)
    }
    fun getEvent(url:String) {
        _event.postValue(DataState.Loading)
        MarvelRepository.getEventsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                  _event.postValue(DataState.Success(it))
                },
                {
                    _event.postValue(DataState.Error(it))
                }).addTo(compositeDisposable)
    }

   private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}