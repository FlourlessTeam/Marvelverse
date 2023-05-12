package com.example.marvelverse.app.ui.series.details

import androidx.lifecycle.LiveData
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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel() : BaseViewModel(), ComicInteractionListener,
    EventInteractionListener,
    CharacterInteractionListener {

    private val _characters: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val _detailsSeries: MutableLiveData<DetailsSeries> = MutableLiveData()

    val characters: LiveData<DataState<Character>>
        get() = _characters
    val comics: LiveData<DataState<Comic>>
        get() = _comics
    val events: LiveData<DataState<Event>>
        get() = _events
    val detailsSeries: LiveData<DetailsSeries>
        get() = _detailsSeries

    fun getCharacters(url: String) {

       MarvelRepository.getCharactersByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characters ->
                    _characters.postValue(DataState.Success(characters))
                },
                {
                    _characters.postValue(DataState.Error(it))
                }
            ).addTo(disposables)
    }

    fun getComics(url: String) {

        MarvelRepository.getComicsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { comics ->
                    _comics.postValue(DataState.Success(comics))
                },
                {
                    _comics.postValue(DataState.Error(it))
                }
            ).addTo(disposables)

    }

    fun getEvents(url: String) {
        val disposable = MarvelRepository.getEventsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { events ->
                    _events.postValue(DataState.Success(events))
                },
                {
                    _events.postValue(DataState.Error(it))
                }
            ).addTo(disposables)
    }



    override fun onCharacterClick(character: Character) {
        _detailsSeries.postValue(DetailsSeries.ClickCharacterSeries(character))
    }

    override fun onComicClick(comic: Comic) {
        _detailsSeries.postValue(DetailsSeries.ClickComicSeries(comic))
    }

    override fun onEventClick(event: Event) {
        _detailsSeries.postValue(DetailsSeries.ClickEventSeries(event))
    }

    fun clearEvents() {
        if (_detailsSeries.value != DetailsSeries.ReadyState)
            _detailsSeries.postValue(DetailsSeries.ReadyState)
    }
}