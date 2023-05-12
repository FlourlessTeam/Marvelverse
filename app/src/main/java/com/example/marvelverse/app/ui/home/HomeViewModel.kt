package com.example.marvelverse.app.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.interfaces.ParentInteractionListener
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import io.reactivex.rxjava3.core.Single

class HomeViewModel : BaseViewModel(), ParentInteractionListener,
    CharacterInteractionListener, EventInteractionListener, ComicInteractionListener,
    SeriesInteractionListener {
    private val _homeItems: MutableLiveData<DataState<HomeItem>> = MutableLiveData()
    val homeItems: LiveData<DataState<HomeItem>> = _homeItems

    private val _homeEvents: MutableLiveData<HomeEvent> = MutableLiveData()
    val homeEvents: LiveData<HomeEvent> = _homeEvents

    init {
        getDataForHomeItems()
    }

    @SuppressLint("CheckResult")
    fun getDataForHomeItems() {
        _homeItems.postValue(DataState.Loading)
        Single.zip(
            MarvelRepository.getRandomComics(),
            MarvelRepository.getRandomEvents(),
            MarvelRepository.getRandomCharacters(),
            MarvelRepository.getRandomSeries()
        ) { comics, events, characters, series ->
            listOf(
                HomeItem.CharactersItem(characters),
                HomeItem.ComicsItem(comics),
                HomeItem.EventsItem(events),
                HomeItem.SeriesItem(series)
            )
        }
            .applySchedulers()
            .subscribe({ homeItems ->
                _homeItems.postValue(DataState.Success(homeItems))
            }, {
                _homeItems.postValue(DataState.Error(it))
            }).addTo(disposables)
    }


    override fun onCharacterClick(character: Character) {
        _homeEvents.postValue(HomeEvent.ClickCharacterEvent(character))
    }

    override fun onEventClick(event: Event) {
        _homeEvents.postValue(HomeEvent.ClickEventEvent(event))
    }

    override fun onComicClick(comic: Comic) {
        _homeEvents.postValue(HomeEvent.ClickComicEvent(comic))
    }

    override fun onSeriesClick(series: Series) {
        _homeEvents.postValue(HomeEvent.ClickSeriesEvent(series))
    }

    override fun onViewAllCharactersClick() {
        _homeEvents.postValue(HomeEvent.ClickSeeAllCharactersEvent)
    }

    override fun onViewAllEventsClick() {
        _homeEvents.postValue(HomeEvent.ClickSeeAllEventsEvent)
    }

    override fun onViewAllComicsClick() {
        _homeEvents.postValue(HomeEvent.ClickSeeAllComicsEvent)
    }


    override fun onViewAllSeriesClick() {
        _homeEvents.postValue(HomeEvent.ClickSeeAllSeriesEvent)
    }

    fun clearEvents() {
        if (homeEvents.value != HomeEvent.ReadyState)
            _homeEvents.postValue(HomeEvent.ReadyState)
    }

}
