package com.example.marvelverse.app.ui.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Comic
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ComicViewModel: ViewModel() {
    private val repository = MarvelRepository
    private val _comicsData = MutableLiveData<MutableList<Comic>>()
    val comicData: LiveData<MutableList<Comic>>
        get() = _comicsData
    private val compositeDisposable = CompositeDisposable()

    fun getComics() {
        val disposable = repository.searchComics().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _comicsData.postValue(it as MutableList<Comic>?) }, {})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}