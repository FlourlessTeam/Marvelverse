package com.example.marvelverse.data.repositories

import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.domain.entities.main.Character
import io.reactivex.rxjava3.core.Single


object MarvelRepository {
    private val marvelApiServices by lazy {
        RetrofitClient.marvelApiServices
    }

    fun searchComics(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchComics(limit, title).map { it.data.results }

    fun searchSeries(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchSeries(limit, title).map { it.data.results }


    fun searchCharacters(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchCharacters(limit, title).map { it.data.results }


    fun searchEvents(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchEvents(limit, title).map { it.data.results }


    fun searchStories(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchStories(limit, title).map { it.data.results }


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
        marvelApiServices.fetchCharacters(50, null).map { it.data.results.shuffled().take(10) }

    fun getRandomComics() =
        marvelApiServices.fetchComics(30, null).map { it.data.results.shuffled().take(5) }


    fun getRandomSeries() =
        marvelApiServices.fetchSeries(30, null).map { it.data.results.shuffled().take(5) }


    fun getRandomEvents() =
        marvelApiServices.fetchEvents(30, null).map { it.data.results.shuffled().take(5) }

    fun getRandomStories() =
        marvelApiServices.fetchStories(30, null).map { it.data.results.shuffled().take(5) }

    fun fetchHomeItems() =
        Single.zip(
            getRandomCharacters(),
            getRandomComics(),
            getRandomStories(),
            getRandomEvents(),
            getRandomSeries()
        ) { characters: List<Character>, comics: List<Comic>, stories: List<Story>, events: List<Event>, series: List<Series> ->
            listOf(
                HomeItem.CharactersItem(characters),
                HomeItem.ComicsItem(comics),
                HomeItem.StoriesItem(stories),
                HomeItem.EventsItem(events),
                HomeItem.SeriesItem(series)
            )
        }


}