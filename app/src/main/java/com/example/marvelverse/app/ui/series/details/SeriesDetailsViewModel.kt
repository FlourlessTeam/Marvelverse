package com.example.marvelverse.app.ui.series.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class SeriesDetailsViewModel() : ViewModel(), ComicInteractionListener, EventInteractionListener,
    CharacterInteractionListener {

    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    private val _comics: MutableLiveData<List<Comic>> = MutableLiveData()
    private val _events: MutableLiveData<List<Event>> = MutableLiveData()
    private val disposables = CompositeDisposable()
    private val _detailsSeries: MutableLiveData<DetailsSeries> = MutableLiveData()

    val characters: LiveData<List<Character>>
        get() = _characters
    val comics: LiveData<List<Comic>>
        get() = _comics
    val events: LiveData<List<Event>>
        get() = _events
    val detailsSeries: LiveData<DetailsSeries>
        get() = _detailsSeries

    fun getCharacters(url: String) {

        val disposable = MarvelRepository.getCharactersByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characters ->
                    _characters.postValue(characters)
                },
                {
                }
            )
        disposables.add(disposable)
    }

    fun getComics(url: String) {

        val disposable = MarvelRepository.getComicsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { comics ->
                    _comics.postValue(comics)
                },
                {

                }
            )
        disposables.add(disposable)

    }

    fun getEvents(url: String) {
        val disposable = MarvelRepository.getEventsByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { events ->
                    _events.postValue(events)
                },
                {
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

    fun clearEvents() {
        if (_detailsSeries.value != DetailsSeries.ReadyState)
            _detailsSeries.postValue(DetailsSeries.ReadyState)
    }
}