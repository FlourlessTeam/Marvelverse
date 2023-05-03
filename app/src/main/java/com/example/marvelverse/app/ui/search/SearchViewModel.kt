package com.example.marvelverse.app.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.wrappers.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
class SearchViewModel : ViewModel() {


    private val repositry = MarvelRepository

    private val _comices = MutableLiveData<List<Comic>>()
    val comices: LiveData<List<Comic>>
        get() = _comices

    private val _character =
        MutableLiveData<List<com.example.marvelverse.domain.entities.main.Character>>()
    val character: LiveData<List<com.example.marvelverse.domain.entities.main.Character>>
        get() = _character

    private val _creator = MutableLiveData<List<Creator>>()
    val creator: LiveData<List<Creator>>
        get() = _creator

    private val _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>>
        get() = _event

    fun comicSearch(limit: Int?, title: String?) {
        repositry.searchComics(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onComicsSearchSuccess, ::onSearchError)
    }

    fun characterSearch(limit: Int?, title: String?) {
        repositry.searchCharacters(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCharacterSearchSuccess, ::onSearchError)
    }

    fun creatorSearch(limit: Int?, title: String?) {
        repositry.searchCreators(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCreatorSearchSuccess, ::onSearchError)
    }

    fun eventSearch(limit: Int?, title: String?) {
        repositry.searchEvents(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onEventSearchSuccess, ::onSearchError)
    }


    private fun onComicsSearchSuccess(comics: List<Comic>) {
        _comices.postValue(comics)
    }
    private fun onCharacterSearchSuccess(characters: List<com.example.marvelverse.domain.entities.main.Character>) {
        _character.postValue(characters)
    }
    private fun onCreatorSearchSuccess(creators: List<Creator>) {
        _creator.postValue(creators)
    }
    private fun onEventSearchSuccess(events: List<Event>) {
        _event.postValue(events)
    }

    private fun onSearchError(throwable: Throwable) {}


}
