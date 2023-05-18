package com.example.marvelverse.app.ui.search


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.search.utils.SearchEvent
import com.example.marvelverse.app.ui.search.utils.SearchFilter
import com.example.marvelverse.app.ui.search.utils.SearchItems
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.SearchKeyword
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: MarvelRepository) :
    BaseViewModel(), CharacterInteractionListener,
    ComicInteractionListener, EventInteractionListener {

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


    private val _searchEvent = MutableLiveData<SingleEventState<SearchEvent>>()
    val searchEvent: LiveData<SingleEventState<SearchEvent>> get() = _searchEvent

    private val _searchResult = MediatorLiveData<SearchItems>()
    val searchResult: LiveData<SearchItems> = _searchResult
    init {
        searchFilterOption.postValue(SearchFilter.Character)
        _searchResult.addSource(_comicList) { comics ->
            _searchResult.value = SearchItems(comics, _characterList.value ?: DataState.Empty, _eventList.value ?: DataState.Empty)
        }

        _searchResult.addSource(_characterList) { characters ->
            _searchResult.value = SearchItems(_comicList.value ?: DataState.Empty, characters, _eventList.value ?: DataState.Empty)
        }

        _searchResult.addSource(_eventList) { events ->
            _searchResult.value = SearchItems(_comicList.value ?: DataState.Empty, _characterList.value ?: DataState.Empty, events)
        }

    }

    fun comicSearch(limit: Int?, title: String) {
        _comicList.postValue(DataState.Loading)
        repository.searchCachedComics(limit, title)
            .subscribeBy(::onComicsSearchSuccess, ::onSearchError)
    }

    fun characterSearch(limit: Int?, title: String) {
        _characterList.postValue(DataState.Loading)
        repository.searchCacheCharacters(limit, title)
            .subscribeBy(::onCharacterSearchSuccess, ::onSearchError)
    }

    private fun onSearchError(throwable: Throwable) {
        _characterList.postValue(DataState.Error(throwable))
        _comicList.postValue(DataState.Error(throwable))
        _eventList.postValue(DataState.Error(throwable))
    }

    fun eventSearch(limit: Int?, title: String) {
        _eventList.postValue(DataState.Loading)
        repository.searchCachedEvents(limit, title)
            .subscribeBy(::onEventSearchSuccess, ::onSearchError)
    }

    private fun onComicsSearchSuccess(comics: List<Comic>) {
        if (comics.isEmpty()){
            _comicList.postValue(DataState.Empty)
        }else{
            _comicList.postValue(DataState.Success(comics))
        }

    }

    private fun onCharacterSearchSuccess(characters: List<Character>) {
        if (characters.isEmpty()){
            _characterList.postValue(DataState.Empty)
        }else{
            _characterList.postValue(DataState.Success(characters))
        }
    }

    private fun onEventSearchSuccess(events: List<Event>) {
        if (events.isEmpty()){
            _eventList.postValue(DataState.Empty)
        }else{
            _eventList.postValue(DataState.Success(events))
        }
    }

    override fun onCharacterClick(character:Character) {
        _searchEvent.postValue(SingleEventState(SearchEvent.ClickCharacterEvent(character)))
    }

    override fun onComicClick(comic: Comic) {
        _searchEvent.postValue(SingleEventState(SearchEvent.ClickComicEvent(comic)))
    }

    override fun onEventClick(event: Event) {
        _searchEvent.postValue(SingleEventState(SearchEvent.ClickEventEvent(event)))
    }

    fun onSearchFilterOptionSelected(searchFilter: SearchFilter) {
        this.searchFilterOption.postValue(searchFilter)
        _characterList.postValue(DataState.Empty)
        _comicList.postValue(DataState.Empty)
        _eventList.postValue(DataState.Empty)
    }

    fun showKeywordSuggests() {
        _characterList.postValue(DataState.ShowKeywordSuggests)
        _comicList.postValue(DataState.ShowKeywordSuggests)
        _eventList.postValue(DataState.ShowKeywordSuggests)
    }

    fun cacheKeyword(keyword: SearchKeyword) {
        repository.saveKeyword(keyword)
    }
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        repository.clearDisposables()
    }

}
