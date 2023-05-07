package com.example.marvelverse.app.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.bottomSheet.BottomSheetListener
import com.example.marvelverse.app.ui.characters.CharactersEvent
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


enum class SearchFilter {
    Character,
    Comic,
    Event,
}

@SuppressLint("CheckResult")
class SearchViewModel : ViewModel(), BottomSheetListener, CharacterInteractionListener,
    ComicInteractionListener, EventInteractionListener {

    private val repositry = MarvelRepository

    var searchFilterOption: MutableLiveData<SearchFilter> =
        MutableLiveData<SearchFilter>(SearchFilter.Character)

    private val compositeDisposable = CompositeDisposable()

    private val _itemList = MutableLiveData<DataState<Any>>()
    val itemList: LiveData<DataState<Any>>
        get() = _itemList

    private val _searchEvent = MutableLiveData<SearchEvent>()
    val searchEvent: LiveData<SearchEvent> get() = _searchEvent

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

    private fun onCharacterSearchSuccess(characters: List<Character>) {
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
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onSearchFilterOptionSelected(searchFilter: SearchFilter) {
        this.searchFilterOption.postValue(searchFilter)
        when (searchFilter) {
            SearchFilter.Character -> characterSearch(null, null)
            SearchFilter.Comic -> comicSearch(null, null)
            SearchFilter.Event -> eventSearch(null, null)
        }
    }

    override fun onCharacterClick(character: Character) {
        _searchEvent.postValue(SearchEvent.ClickCharacterEvent(character))
    }

    override fun onComicClick(comic: Comic) {
        _searchEvent.postValue(SearchEvent.ClickComicEvent(comic))
    }

    override fun onEventClick(event: Event) {
        _searchEvent.postValue(SearchEvent.ClickEventEvent(event))
    }
    fun clearEvents() {
        if (_searchEvent.value != SearchEvent.ReadyState)
            _searchEvent.postValue(SearchEvent.ReadyState)
    }
}
