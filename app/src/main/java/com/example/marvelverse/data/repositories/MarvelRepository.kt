package com.example.marvelverse.data.repositories

import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.data.local.FakeLocalData
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.core.Single


object MarvelRepository {
    private val marvelApiServices by lazy {
        RetrofitClient.marvelApiServices
    }

    private val fakeLocalData = FakeLocalData()

    fun searchComics(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchComics(limit, title).map { it.data.results }

    fun searchSeries(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchSeries(limit, title).map { it.data.results }


    fun searchCharacters(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchCharacters(limit, title).map { it.data.results }


    fun searchEvents(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchEvents(limit, title).map { it.data.results }


    fun searchStories(limit: Int? = null) =
        marvelApiServices.fetchStories(limit).map { it.data.results }


    fun searchCreators(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchCreators(limit, title).map { it.data.results }

    fun getComicsByUrl(url: String) =
        marvelApiServices.fetchComicsByUrl(url).map { it.data.results }

    fun getStoriesByUrl(url: String) =
        marvelApiServices.fetchStoriesByUrl(url).map { it.data.results }

    fun getSeriesByUrl(url: String) =
        marvelApiServices.fetchSeriesByUrl(url).map { it.data.results }

    fun getCharactersByUrl(url: String) =
        marvelApiServices.fetchCharactersByUrl(url).map { it.data.results }

    fun getCreatorsByUrl(url: String) =
        marvelApiServices.fetchCreatorsByUrl(url).map { it.data.results }

    fun getEventsByUrl(url: String) =
        marvelApiServices.fetchEventsByUrl(url).map { it.data.results }


    fun getRandomCharacters() =
        marvelApiServices.fetchCharacters(80, null).map { it.data.results.shuffled().take(20) }

    fun getRandomComics() =
        marvelApiServices.fetchComics(50, null).map { it.data.results.shuffled().take(10) }


    fun getRandomSeries() =
        marvelApiServices.fetchSeries(50, null).map { it.data.results.shuffled().take(10) }


    fun getRandomEvents() =
        marvelApiServices.fetchEvents(50, null).map { it.data.results.shuffled().take(10) }

    fun getItems() = fakeLocalData.getAboutItems()


}