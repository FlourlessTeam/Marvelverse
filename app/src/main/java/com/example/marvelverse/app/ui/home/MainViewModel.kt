package com.example.marvelverse.app.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val characters: MutableLiveData<List<Character>> = MutableLiveData()
    val comics: MutableLiveData<List<Comic>> = MutableLiveData()
    val stories: MutableLiveData<List<Story>> = MutableLiveData()
    val events: MutableLiveData<List<Event>> = MutableLiveData()
    val creators: MutableLiveData<List<Creator>> = MutableLiveData()
    val series: MutableLiveData<List<Series>> = MutableLiveData()

    val errorMessage:MutableLiveData<String> = MutableLiveData()

    init {
        getRandomCharacters()
        getRandomComics()
        getRandomStories()
        getRandomEvents()
        getRandomCreators()
        getRandomSeries()
    }
    @SuppressLint("CheckResult")
    fun getRandomCharacters() {
        MarvelRepository.getRandomCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                characters.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getRandomComics() {
        MarvelRepository.getRandomComics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                comics.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getRandomStories() {
        MarvelRepository.getRandomStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stories.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getRandomEvents() {
        MarvelRepository.getRandomEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                events.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getRandomCreators() {
        MarvelRepository.getRandomCreators()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                creators.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }

    @SuppressLint("CheckResult")
    fun getRandomSeries() {
        MarvelRepository.getRandomSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                series.postValue(it)
            }, {
                errorMessage.postValue(it.message)
            })
    }
}