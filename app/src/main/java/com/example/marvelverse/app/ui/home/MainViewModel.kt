package com.example.marvelverse.app.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.app.ui.home.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel(), ParentInteractionListener,
    CharacterInteractionListener, EventInteractionListener, ComicInteractionListener,SeriesInteractionListener{

    val homeItems: MutableLiveData<List<HomeItem>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val hoveEvents: MutableLiveData<HomeEvent> = MutableLiveData()

    init {
        getDataForHomeItems()
    }
    @SuppressLint("CheckResult")
    fun getDataForHomeItems() {
        MarvelRepository.fetchHomeItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ homeItems ->
                this.homeItems.value = homeItems
            }, { error ->
                errorMessage.value = error.message
            })
    }

    override fun onCharacterClick(character: Character) {
        hoveEvents.value = (HomeEvent.ClickCharacterEvent(character))
    }

    override fun onEventClick(event: Event) {
        hoveEvents.value = (HomeEvent.ClickEventEvent(event))
    }

    override fun onComicClick(comic: Comic) {
        hoveEvents.value = (HomeEvent.ClickComicEvent(comic))
    }

    override fun onStoriesClick(stories: Story) {
        hoveEvents.value = (HomeEvent.ClickStoryEvent(stories))
    }

    override fun onSeriesClick(series: Series) {
        hoveEvents.value = (HomeEvent.ClickSeriesEvent(series))
    }

    override fun onViewAllCharactersClick() {
        hoveEvents.value = (HomeEvent.ClickSeeAllCharactersEvent)
    }

    override fun onViewAllEventsClick() {
        hoveEvents.value = (HomeEvent.ClickSeeAllEventsEvent)
    }

    override fun onViewAllComicsClick() {
        hoveEvents.value = (HomeEvent.ClickSeeAllComicsEvent)
    }

    override fun onViewAllStoriesClick() {
        hoveEvents.value = (HomeEvent.ClickSeeAllStoriesEvent)
    }

    override fun onViewAllSeriesClick() {
        hoveEvents.value = (HomeEvent.ClickSeeAllSeriesEvent)
    }
}
