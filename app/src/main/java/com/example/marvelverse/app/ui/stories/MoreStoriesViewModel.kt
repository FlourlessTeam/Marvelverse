package com.example.marvelverse.app.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoreStoriesViewModel : ViewModel() {
    private val repository = MarvelRepository
    private val _storiesData = MutableLiveData<MutableList<Story>>()
    val storiesData: LiveData<MutableList<Story>>
        get() = _storiesData
    private val compositeDisposable = CompositeDisposable()

    fun getStories() {
        val disposable = repository.searchStories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _storiesData.postValue(it as MutableList<Story>?) }, {})
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}