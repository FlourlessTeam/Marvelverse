package com.example.marvelverse.app.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic

class ComicViewModel : BaseViewModel(), ComicInteractionListener {

    private var _Comic = MutableLiveData<DataState<Comic>>()
    val comic: LiveData<DataState<Comic>> get() = _Comic

    private val _comicEvent = MutableLiveData<ComicEvent>()
    val comicEvent: LiveData<ComicEvent> get() = _comicEvent

    init {
        getComic()
    }

    private fun getComic() {
        _Comic.postValue(DataState.Loading)
        MarvelRepository.searchComics()
            .applySchedulers()
            .subscribe(
                {
                    _Comic.postValue(DataState.Success(it))
                },
                {
                    _Comic.postValue(DataState.Error(it))
                }
            ).addTo(disposables)
    }


    override fun onComicClick(comic: Comic) {
        _comicEvent.postValue(ComicEvent.ClickComicEvent(comic))
    }

    fun backToHome() {
        _comicEvent.postValue(ComicEvent.BackToHome)
    }


    fun clearEvents() {
        if (_comicEvent.value != ComicEvent.ReadyState)
            _comicEvent.postValue(ComicEvent.ReadyState)
    }

}