package com.example.marvelverse.app.ui.characters.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series


class DetailsCharacterViewModel : BaseViewModel(), ComicInteractionListener,
    EventInteractionListener,
    SeriesInteractionListener {

    private val _character: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val _series: MutableLiveData<DataState<Series>> = MutableLiveData()
    private val _characterDetails: MutableLiveData<DetailsCharacterEvents> = MutableLiveData()


    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val events: LiveData<DataState<Event>>
        get() = _events
    val series: LiveData<DataState<Series>>
        get() = _series
    val characters: LiveData<DataState<Character>>
        get() = _character
    val characterDetails: LiveData<DetailsCharacterEvents>
        get() = _characterDetails

    fun getEvent(url: String) {
        val single = MarvelRepository.getEventsByUrl(url).applySchedulers()
        val dispose = single.subscribe({
            _events.postValue(DataState.Success(it))
        }, {
            _events.postValue(DataState.Error(it))
        }).addTo(disposables)
    }


    fun getComics(url: String) {
        val single = MarvelRepository.getComicsByUrl(url).applySchedulers()
        val dispose = single.subscribe({
                _comics.postValue(DataState.Success(it))
            }, {
                _comics.postValue(DataState.Error(it))
            }).addTo(disposables)


    }

    fun getSeries(url: String) {
        val single = MarvelRepository.getSeriesByUrl(url).applySchedulers()
        val dispose = single.subscribe({
                _series.postValue(DataState.Success(it))
            }, {
                _series.postValue(DataState.Error(it))
            }).addTo(disposables)

    }



    override fun onComicClick(comic: Comic) {
        _characterDetails.postValue(DetailsCharacterEvents.ClickComicEvent(comic))
    }

    override fun onEventClick(event: Event) {
        _characterDetails.postValue(DetailsCharacterEvents.ClickEventEvent(event))
    }

    override fun onSeriesClick(series: Series) {
        _characterDetails.postValue(DetailsCharacterEvents.ClickSeriesEvent(series))
    }

    fun clearEvents() {
        if (_characterDetails.value != DetailsCharacterEvents.ReadyState)
            _characterDetails.postValue(DetailsCharacterEvents.ReadyState)
    }
}