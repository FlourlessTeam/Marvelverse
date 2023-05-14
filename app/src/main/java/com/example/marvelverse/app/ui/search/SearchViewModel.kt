package com.example.marvelverse.app.ui.search

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event


enum class SearchFilter {
    Character, Comic, Event,
}

data class SearchItems(
    val comics: DataState<Comic>,
    val characters: DataState<Character>,
    val events: DataState<Event>
)

@SuppressLint("CheckResult")
class SearchViewModel : BaseViewModel(), CharacterInteractionListener,
    ComicInteractionListener, EventInteractionListener {

    private val repositry = MarvelRepository

    val searchFilterOption = MutableLiveData(SearchFilter.Character)

    private val _comicList = MutableLiveData<DataState<Comic>>()
    val comicList: LiveData<DataState<Comic>>
        get() = _comicList

    private val _characterList = MutableLiveData<DataState<Character>>()
    val characterList: LiveData<DataState<Character>>
        get() = _characterList

    private val _eventList = MutableLiveData<DataState<Event>>()
    val eventList: LiveData<DataState<Event>>
        get() = _eventList


    private val _searchEvent = MutableLiveData<SearchEvent>()
    val searchEvent: LiveData<SearchEvent> get() = _searchEvent

    private val _searchResult = MediatorLiveData<SearchItems>()
    val searchResult: LiveData<SearchItems> = _searchResult
    init {
        searchFilterOption.postValue(SearchFilter.Character)
        _searchResult.addSource(_comicList) { comics ->
            _searchResult.value = SearchItems(comics, _characterList.value ?: DataState.NoResult, _eventList.value ?: DataState.NoResult)
        }

        _searchResult.addSource(_characterList) { characters ->
            _searchResult.value = SearchItems(_comicList.value ?: DataState.NoResult, characters, _eventList.value ?: DataState.NoResult)
        }

        _searchResult.addSource(_eventList) { events ->
            _searchResult.value = SearchItems(_comicList.value ?: DataState.NoResult, _characterList.value ?: DataState.NoResult, events)
        }

    }

    fun comicSearch(limit: Int?, title: String?) {
        _comicList.postValue(DataState.Loading)
        repositry.searchComics(limit, title).applySchedulers()
            .subscribe(::onComicsSearchSuccess, ::onSearchError).addTo(disposables)
    }

    fun characterSearch(limit: Int?, title: String?) {
        _characterList.postValue(DataState.Loading)
        repositry.searchCharacters(limit, title).applySchedulers()
            .subscribe(::onCharacterSearchSuccess, ::onSearchError).addTo(disposables)
    }

    private fun onSearchError(throwable: Throwable) {
        _characterList.postValue(DataState.Error(throwable))
        _comicList.postValue(DataState.Error(throwable))
        _eventList.postValue(DataState.Error(throwable))
    }

    fun eventSearch(limit: Int?, title: String?) {
        _eventList.postValue(DataState.Loading)
        repositry.searchEvents(limit, title).applySchedulers()
            .subscribe(::onEventSearchSuccess, ::onSearchError).addTo(disposables)
    }

    private fun onComicsSearchSuccess(comics: List<Comic>) {
        if (comics.isEmpty()){
            _comicList.postValue(DataState.NoResult)
        }else{
            _comicList.postValue(DataState.Success(comics))
        }

    }

    private fun onCharacterSearchSuccess(characters: List<Character>) {
        if (characters.isEmpty()){
            _characterList.postValue(DataState.NoResult)
        }else{
            _characterList.postValue(DataState.Success(characters))
        }
    }

    private fun onEventSearchSuccess(events: List<Event>) {
        if (events.isEmpty()){
            _eventList.postValue(DataState.NoResult)
        }else{
            _eventList.postValue(DataState.Success(events))
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
        if (_searchEvent.value != SearchEvent.ReadyState) _searchEvent.postValue(SearchEvent.ReadyState)
    }

    fun onSearchFilterOptionSelected(searchFilter: SearchFilter) {
        this.searchFilterOption.postValue(searchFilter)
        _characterList.postValue(DataState.NoResult)
        _comicList.postValue(DataState.NoResult)
        _eventList.postValue(DataState.NoResult)
    }


}
