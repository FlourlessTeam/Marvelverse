package com.example.marvelverse.app.ui.characters.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.EventInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.SeriesInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class DetailsCharacterViewModel : ViewModel(), ComicInteractionListener, EventInteractionListener,
    SeriesInteractionListener {

    private val compositeDisposable by lazy { CompositeDisposable() }
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
        val single = MarvelRepository.getEventsByUrl(url).subscribeOn(Schedulers.io())
        val dispose = single.observeOn(AndroidSchedulers.mainThread()).subscribe({
            _events.postValue(DataState.Success(it))
        }, {
            _events.postValue(DataState.Error(it))
        })
        compositeDisposable.add(dispose)
    }


    fun getComics(url: String) {
        val single = MarvelRepository.getComicsByUrl(url).subscribeOn(Schedulers.io())
        val dispose = single.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _comics.postValue(DataState.Success(it))
            }, {
                _comics.postValue(DataState.Error(it))
            })

        compositeDisposable.add(dispose)

    }

    fun getSeries(url: String) {
        val single = MarvelRepository.getSeriesByUrl(url).subscribeOn(Schedulers.io())
        val dispose = single.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _series.postValue(DataState.Success(it))
            }, {
                _series.postValue(DataState.Error(it))
            })
        compositeDisposable.add(dispose)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
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