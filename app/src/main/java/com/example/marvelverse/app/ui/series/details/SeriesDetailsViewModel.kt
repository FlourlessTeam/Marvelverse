package com.example.marvelverse.app.ui.series.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.events.details.DetailsEvent
import com.example.marvelverse.app.ui.home.interfaces.CharacterInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.app.ui.home.interfaces.EventInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesDetailsViewModel():ViewModel(),ComicInteractionListener,EventInteractionListener,CharacterInteractionListener {
    private val _charactersState: MutableLiveData<DataState<Character>> = MutableLiveData()
    private val _comicsState: MutableLiveData<DataState<Comic>> = MutableLiveData()
    private val _eventsState: MutableLiveData<DataState<Event>> = MutableLiveData()
    private val disposables = CompositeDisposable()
    private val _detailsSeries: MutableLiveData<DetailsSeries> = MutableLiveData()

    val charactersState: LiveData<DataState<Character>>
        get() = _charactersState
    val comicsState: LiveData<DataState<Comic>>
        get() = _comicsState
    val eventsState: LiveData<DataState<Event>>
        get() = _eventsState
    val detailsSeries: LiveData<DetailsSeries>
        get() = _detailsSeries
    fun getCharacters(url: String) {
        _charactersState.postValue(DataState.Loading)

        val disposable =  MarvelRepository.getCharactersByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characters ->
                    _charactersState.postValue(DataState.Success(characters))
                },
                { error ->
                    _charactersState.postValue(DataState.Error(error))
                }
            )
        disposables.add(disposable)
    }





    fun getComics(url: String) {
        _comicsState.postValue(DataState.Loading)
        val disposable =   MarvelRepository.getComicsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { comics ->
                    _comicsState.postValue(DataState.Success(comics))
                },
                { error ->
                    _comicsState.postValue(DataState.Error(error))
                }
            )
        disposables.add(disposable)

    }


    fun getEvents(url: String) {
        _eventsState.postValue(DataState.Loading)
        val disposable =  MarvelRepository.getEventsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { events ->
                    _eventsState.postValue(DataState.Success(events))
                },
                { error ->
                    _eventsState.postValue(DataState.Error(error))
                }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
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
}