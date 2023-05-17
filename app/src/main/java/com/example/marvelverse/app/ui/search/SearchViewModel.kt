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
import com.example.marvelverse.data.dataSources.local.MarvelDatabase
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event





class SearchViewModel : BaseViewModel(), CharacterInteractionListener,
    ComicInteractionListener, EventInteractionListener {

    private val repositry = MarvelRepository()

    val searchFilterOption = MutableLiveData(SearchFilter.Character)

    private val _comicList = MutableLiveData<DataState<Comic>>()
    val comicList: LiveData<DataState<Comic>>
        get() = _comicList

    private val _characterList = MutableLiveData<DataState<com.example.marvelverse.domain.entities.Character>>()
    val characterList: LiveData<DataState<com.example.marvelverse.domain.entities.Character>>
        get() = _characterList

    private val _eventList = MutableLiveData<DataState<Event>>()
    val eventList: LiveData<DataState<Event>>
        get() = _eventList

    // TODO: Remove
    fun initDb(db: MarvelDatabase) {
        repositry.db = db
    }


    private val _searchEvent = MutableLiveData<SearchEvent>()
    val searchEvent: LiveData<SearchEvent> get() = _searchEvent

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
        repositry.searchCachedComics(limit, title).applySchedulers()
            .subscribe(::onComicsSearchSuccess, ::onSearchError).addTo(disposables)
    }

    fun characterSearch(limit: Int?, title: String) {
        _characterList.postValue(DataState.Loading)
        repositry.searchCacheCharacters(limit, title).applySchedulers()
            .subscribe(::onCharacterSearchSuccess, ::onSearchError).addTo(disposables)
    }

    private fun onSearchError(throwable: Throwable) {
        _characterList.postValue(DataState.Error(throwable))
        _comicList.postValue(DataState.Error(throwable))
        _eventList.postValue(DataState.Error(throwable))
    }

    fun eventSearch(limit: Int?, title: String) {
        _eventList.postValue(DataState.Loading)
        repositry.searchCachedEvents(limit, title).applySchedulers()
            .subscribe(::onEventSearchSuccess, ::onSearchError).addTo(disposables)
    }

    private fun onComicsSearchSuccess(comics: List<Comic>) {
        if (comics.isEmpty()){
            _comicList.postValue(DataState.Empty)
        }else{
            _comicList.postValue(DataState.Success(comics))
        }

    }

    private fun onCharacterSearchSuccess(characters: List<com.example.marvelverse.domain.entities.Character>) {
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

    override fun onCharacterClick(character: com.example.marvelverse.domain.entities.Character) {
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
        _characterList.postValue(DataState.Empty)
        _comicList.postValue(DataState.Empty)
        _eventList.postValue(DataState.Empty)
    }

    fun showKeywordSuggests() {
        _characterList.postValue(DataState.ShowKeywordSuggests)
        _comicList.postValue(DataState.ShowKeywordSuggests)
        _eventList.postValue(DataState.ShowKeywordSuggests)
    }






}
