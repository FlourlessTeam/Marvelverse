package com.example.marvelverse.app.ui.search

import android.annotation.SuppressLint
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.ListAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.creators.MoreCreatorsAdapter
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Thread.State


enum class SearchFilter{
    Character,
    Comic,
    Event,
    Creator
}

@SuppressLint("CheckResult")
class SearchViewModel : ViewModel() , BottomSheetListener {

    private val repositry = MarvelRepository

    var searchFilterOption:MutableLiveData<SearchFilter> = MutableLiveData<SearchFilter>(SearchFilter.Character)

    private val compositeDisposable = CompositeDisposable()

    private val _comices = MutableLiveData<DataState<Comic>>()
    val comices: LiveData<DataState<Comic>>
        get() = _comices

    private val _character =
        MutableLiveData<DataState<com.example.marvelverse.domain.entities.main.Character>>()
    val character: LiveData<DataState<com.example.marvelverse.domain.entities.main.Character>>
        get() = _character

    private val _creator = MutableLiveData<DataState<Creator>>()
    val creator: LiveData<DataState<Creator>>
        get() = _creator

    private val _event = MutableLiveData<DataState<Event>>()
    val event: LiveData<DataState<Event>>
        get() = _event

    fun comicSearch(limit: Int?, title: String?) {
        _comices.postValue(DataState.Loading)
        compositeDisposable.add(
            repositry.searchComics(limit, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onComicsSearchSuccess, ::onSearchError)
        )
    }

    fun characterSearch(limit: Int?, title: String?) {
        _character.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchCharacters(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCharacterSearchSuccess, ::onSearchError)
        )
    }

    fun creatorSearch(limit: Int?, title: String?) {
        _creator.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchCreators(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCreatorSearchSuccess, ::onSearchError)
        )
    }

    fun eventSearch(limit: Int?, title: String?) {
        _event.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchEvents(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onEventSearchSuccess, ::onSearchError)
        )
    }

    private fun onComicsSearchSuccess(comics: List<Comic>) {
        _comices.postValue(DataState.Success(comics))
    }
    private fun onCharacterSearchSuccess(characters: List<com.example.marvelverse.domain.entities.main.Character>) {
        _character.postValue(DataState.Success(characters))
    }
    private fun onCreatorSearchSuccess(creators: List<Creator>) {
        _creator.postValue(DataState.Success(creators))
    }
    private fun onEventSearchSuccess(events: List<Event>) {
        _event.postValue(DataState.Success(events))
    }

    private fun onSearchError(throwable: Throwable) {
        _comices.postValue(DataState.Error(throwable))
        _character.postValue(DataState.Error(throwable))
        _creator.postValue(DataState.Error(throwable))
        _event.postValue(DataState.Error(throwable))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onSearchFilterOptionSelected(searchFilter: SearchFilter) {
        this.searchFilterOption.postValue(searchFilter)
    }
}
