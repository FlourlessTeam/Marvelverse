package com.example.marvelverse.app.ui.characters.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsCharacterViewModel @Inject constructor(private val repository : MarvelRepository): BaseViewModel(), ComicInteractionListener,
    EventInteractionListener,
    SeriesInteractionListener {

    private val _character: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val _series: MutableLiveData<DataState<Series>> = MutableLiveData()
    private val _characterDetails: MutableLiveData<SingleEventState<DetailsCharacterEvents>> = MutableLiveData()


    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val events: LiveData<DataState<Event>>
        get() = _events
    val series: LiveData<DataState<Series>>
        get() = _series
    val characters: LiveData<DataState<Character>>
        get() = _character
    val characterDetails: LiveData<SingleEventState<DetailsCharacterEvents>>
        get() = _characterDetails

    fun getEvent(url: String) {
        _events.postValue(DataState.Loading)
        repository.getEventsByUrl(url).subscribeBy(::onEventSuccess, ::onEventError)
    }

    private fun onEventSuccess(it: List<Event>) {
        if (it.isEmpty()) {
            _events.postValue(DataState.Empty)
        } else {
            _events.postValue(DataState.Success(it))
        }
    }

    private fun onEventError(it: Throwable) {
        _events.postValue(DataState.Error(it))
    }

    fun getComics(url: String) {
        _comics.postValue(DataState.Loading)
        val single = repository.getComicsByUrl(url)
        single.subscribeBy(::onComicSuccess, ::onComicError)
    }

    private fun onComicSuccess(it: List<Comic>) {
        if (it.isEmpty()) {
            _comics.postValue(DataState.Empty)
        } else {
            _comics.postValue(DataState.Success(it))
        }
    }

    private fun onComicError(it: Throwable) {
        _comics.postValue(DataState.Error(it))
    }

    fun getSeries(url: String) {
        _series.postValue(DataState.Loading)
        repository.getSeriesByUrl(url).subscribeBy(::onSeriesSuccess, ::onSeriesError)
    }

    private fun onSeriesSuccess(it: List<Series>) {
        if (it.isEmpty()) {
            _series.postValue(DataState.Empty)
        } else {
            _series.postValue(DataState.Success(it))
        }
    }

    private fun onSeriesError(it: Throwable) {
        _series.postValue(DataState.Error(it))
    }


    override fun onComicClick(comic: Comic) {
        _characterDetails.postValue(SingleEventState(DetailsCharacterEvents.ClickComicEvent(comic)))
    }

    override fun onEventClick(event: Event) {
        _characterDetails.postValue(SingleEventState(DetailsCharacterEvents.ClickEventEvent(event)))
    }

    override fun onSeriesClick(series: Series) {
        _characterDetails.postValue(SingleEventState(DetailsCharacterEvents.ClickSeriesEvent(series)))
    }

}