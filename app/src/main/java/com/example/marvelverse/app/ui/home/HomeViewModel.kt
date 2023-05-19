package com.example.marvelverse.app.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.interfaces.ParentInteractionListener
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel(),
    ParentInteractionListener,
    CharacterInteractionListener, EventInteractionListener, ComicInteractionListener,
    SeriesInteractionListener {

    private val _homeItems: MutableLiveData<DataState<HomeItem>> = MutableLiveData()
    val homeItems: LiveData<DataState<HomeItem>> = _homeItems

    private val _homeEvent: MutableLiveData<SingleEventState<HomeEvent>> = MutableLiveData()
    val homeEvent: LiveData<SingleEventState<HomeEvent>> = _homeEvent
    init {
        getDataForHomeItems()
    }
    @SuppressLint("CheckResult")
    fun getDataForHomeItems() {
        _homeItems.postValue(DataState.Loading)
        repository.getHomeItems().subscribeBy(this::onSuccess, ::onError)
    }

    private fun onSuccess(homeItem: List<HomeItem>) {
        _homeItems.postValue(DataState.Success(homeItem))
    }

    private fun onError(error: Throwable) {
        _homeItems.postValue(DataState.Error(error))
    }

    override fun onCharacterClick(character: Character) {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickCharacterEvent(character)))
    }

    override fun onEventClick(event: Event) {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickEventEvent(event)))
    }

    override fun onComicClick(comic: Comic) {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickComicEvent(comic)))
    }

    override fun onSeriesClick(series: Series) {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickSeriesEvent(series)))
    }

    override fun onViewAllCharactersClick() {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickSeeAllCharactersEvent))
    }

    override fun onViewAllEventsClick() {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickSeeAllEventsEvent))
    }

    override fun onViewAllComicsClick() {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickSeeAllComicsEvent))
    }


    override fun onViewAllSeriesClick() {
        _homeEvent.postValue(SingleEventState(HomeEvent.ClickSeeAllSeriesEvent))
    }


}
