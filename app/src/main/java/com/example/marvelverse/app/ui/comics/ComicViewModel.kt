package com.example.marvelverse.app.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.utilites.SingleEventState

class ComicViewModel : BaseViewModel(), ComicInteractionListener {

    private var _Comic = MutableLiveData<DataState<Comic>>()
    val comic: LiveData<DataState<Comic>> get() = _Comic

    private val _comicEvent = MutableLiveData<SingleEventState<ComicEvent>>()
    val comicEvent: LiveData<SingleEventState<ComicEvent>> get() = _comicEvent

    init {
        getComic()
    }

    private fun getComic() {
        _Comic.postValue(DataState.Loading)
        MarvelRepository.searchComics().subscribeBy(::onComicSuccess, ::onComicError)
    }
    fun onComicSuccess(it: List<Comic>) {
        if (it.isEmpty()) {
            _Comic.postValue(DataState.Empty)
        } else {
            _Comic.postValue(DataState.Success(it))
        }
    }
    fun onComicError(it: Throwable) {
        _Comic.postValue(DataState.Error(it))
    }


    override fun onComicClick(comic: Comic) {
        _comicEvent.postValue(SingleEventState(ComicEvent.ClickComicEvent(comic)))
    }


}