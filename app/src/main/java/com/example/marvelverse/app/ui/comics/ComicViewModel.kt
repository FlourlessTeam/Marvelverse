package com.example.marvelverse.app.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.DataState
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicViewModel: ViewModel(), ComicInteractionListener {
    private val compositeDisposable = CompositeDisposable()

    private var _Comic = MutableLiveData<DataState<Comic>>()
    val comic: LiveData<DataState<Comic>> get() = _Comic

    private val _comicEvent=MutableLiveData<ComicEvent>()
    val comicEvent:LiveData<ComicEvent> get() = _comicEvent

    init {
        getComic()
    }

    private fun getComic() {
        _Comic.postValue(DataState.Loading)
        MarvelRepository.searchComics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _Comic.postValue(DataState.Success(it))
                },
                {
                    _Comic.postValue(DataState.Error(it))
                }
            ).addTo(compositeDisposable)
    }


    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    override fun onComicClick(comic: Comic) {
        _comicEvent.postValue(ComicEvent.ClickComicEvent(comic))
    }
    fun backToHome(){
        _comicEvent.postValue(ComicEvent.BackToHome)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}