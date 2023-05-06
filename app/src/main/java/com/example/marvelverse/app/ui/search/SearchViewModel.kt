package com.example.marvelverse.app.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.home.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


enum class SearchFilter{
    Character,
    Comic,
    Event,
    Creator
}

@SuppressLint("CheckResult")
class SearchViewModel : ViewModel() , BottomSheetListener , CharacterInteractionListener , ComicInteractionListener , EventInteractionListener {

    private val repositry = MarvelRepository

    var searchFilterOption:MutableLiveData<SearchFilter> = MutableLiveData<SearchFilter>(SearchFilter.Character)

    private val compositeDisposable = CompositeDisposable()
    private val observable:Observable<String> = Observable.create {}

    private val _itemList = MutableLiveData<DataState<Any>>()
    val itemList: LiveData<DataState<Any>>
        get() = _itemList
    init {
        searchFilterOption.postValue(SearchFilter.Character)
    }

    fun comicSearch(limit: Int?, title: String?) {
        _itemList.postValue(DataState.Loading)
        compositeDisposable.add(
            repositry.searchComics(limit, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onComicsSearchSuccess, ::onSearchError)
        )
    }

    fun characterSearch(limit: Int?, title: String?) {
        _itemList.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchCharacters(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCharacterSearchSuccess, ::onSearchError)
        )
    }

    fun creatorSearch(limit: Int?, title: String?) {
        _itemList.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchCreators(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCreatorSearchSuccess, ::onSearchError)
        )
    }

    fun eventSearch(limit: Int?, title: String?) {
        _itemList.postValue(DataState.Loading)
        compositeDisposable.add(
        repositry.searchEvents(limit, title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onEventSearchSuccess, ::onSearchError)
        )
    }

    private fun onComicsSearchSuccess(comics: List<Comic>) {
        _itemList.postValue(DataState.Success(comics))
    }
    private fun onCharacterSearchSuccess(characters: List<com.example.marvelverse.domain.entities.main.Character>) {
        _itemList.postValue(DataState.Success(characters))
    }
    private fun onCreatorSearchSuccess(creators: List<Creator>) {
        _itemList.postValue(DataState.Success(creators))
    }
    private fun onEventSearchSuccess(events: List<Event>) {
        _itemList.postValue(DataState.Success(events))
    }

    private fun onSearchError(throwable: Throwable) {
        _itemList.postValue(DataState.Error(throwable))
        Log.d("TAG" , throwable.message.toString())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onSearchFilterOptionSelected(searchFilter: SearchFilter) {
        this.searchFilterOption.postValue(searchFilter)
        when(searchFilter){
            SearchFilter.Character -> characterSearch(null , null)
            SearchFilter.Comic -> comicSearch(null , null)
            SearchFilter.Event -> eventSearch(null , null)
            SearchFilter.Creator -> creatorSearch(null , null)
        }
    }

    override fun onCharacterClick(character: Character) {


    }

    override fun onComicClick(comic: Comic) {

    }

    override fun onEventClick(event: Event) {

    }
}
