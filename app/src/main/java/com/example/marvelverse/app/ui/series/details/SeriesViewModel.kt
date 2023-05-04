package com.example.marvelverse.app.ui.series.details

import android.annotation.SuppressLint
import android.util.Log
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
import io.reactivex.rxjava3.schedulers.Schedulers

class SeriesViewModel():ViewModel(),ComicInteractionListener,EventInteractionListener,CharacterInteractionListener {
    private val _character: MutableLiveData<List<Character>?> = MutableLiveData()
    private val _comics: MutableLiveData<List<Comic>?> = MutableLiveData()
    private val _event: MutableLiveData<List<Event>?> = MutableLiveData()
    val comics: LiveData<List<Comic>?>
        get() = _comics
    val event: LiveData<List<Event>?>
        get() = _event

    val character: LiveData<List<Character>?>
        get() = _character

    @SuppressLint("CheckResult")
    fun getCharacters(url: String) {
        MarvelRepository.getCharactersByUrl(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("FFF", it.toString())
                _character.postValue(it)
            }, {
                Log.i("FFF", it.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun getComics(url: String) {
        MarvelRepository.getComicsByUrl(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _comics.postValue(it)
            }, {
            })
    }


    @SuppressLint("CheckResult")
    fun getEvent(url: String) {
        MarvelRepository.getEventsByUrl(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _event.postValue(it)
            },{

            })
    }

    override fun onCharacterClick(character: Character) {

    }

    override fun onComicClick(comic: Comic) {
        TODO("Not yet implemented")
    }

    override fun onEventClick(event: Event) {
        TODO("Not yet implemented")
    }
}