package com.example.marvelverse.app.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelverse.utilites.DataState
import com.example.marvelverse.app.ui.base.BaseViewModel
import com.example.marvelverse.app.ui.interfaces.ComicInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.utilites.SingleEventState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel(), ComicInteractionListener {
    private var _comic = MutableLiveData<DataState<Comic>>()
    val comic: LiveData<DataState<Comic>> get() = _comic

    private val _comicEvent = MutableLiveData<SingleEventState<ComicEvent>>()
    val comicEvent: LiveData<SingleEventState<ComicEvent>> get() = _comicEvent

    init {
        getComic()
    }

    private fun getComic() {
        _comic.postValue(DataState.Loading)
        repository.searchComics().subscribeBy(::onComicSuccess, ::onComicError)
    }
    private fun onComicSuccess(it: List<Comic>) {
        if (it.isEmpty()) {
            _comic.postValue(DataState.Empty)
        } else {
            _comic.postValue(DataState.Success(it))
        }
    }
    private fun onComicError(it: Throwable) {
        _comic.postValue(DataState.Error(it))
    }


    override fun onComicClick(comic: Comic) {
        _comicEvent.postValue(SingleEventState(ComicEvent.ClickComicEvent(comic)))
    }


}