package com.example.marvelverse.app.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val homeItems: MutableLiveData<List<HomeItem>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getHomeItems()
    }
    @SuppressLint("CheckResult")
    fun getHomeItems() {
        Single.zip(
            MarvelRepository.getRandomCharacters(),
            MarvelRepository.getRandomComics(),
            MarvelRepository.getRandomStories(),
            MarvelRepository.getRandomEvents(),
            MarvelRepository.getRandomSeries()
        ) { characters: List<Character>, comics: List<Comic>, stories: List<Story>, events: List<Event>, series: List<Series> ->
            listOf(
                HomeItem.CharactersItem(characters),
                HomeItem.ComicsItem(comics),
                HomeItem.StoriesItem(stories),
                HomeItem.EventsItem(events),
                HomeItem.SeriesItem(series)
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ homeItems.postValue(it) }, { errorMessage.postValue(it.message) })
    }
}
