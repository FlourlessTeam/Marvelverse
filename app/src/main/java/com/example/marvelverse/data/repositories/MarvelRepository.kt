package com.example.marvelverse.data.repositories

import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.data.local.FakeLocalData
import com.example.marvelverse.domain.mapper.CharacterMapper
import com.example.marvelverse.domain.mapper.ComicMapper
import com.example.marvelverse.domain.mapper.EventMapper
import com.example.marvelverse.domain.mapper.SeriesMapper


object MarvelRepository {
    private val marvelApiServices by lazy {
        RetrofitClient.marvelApiServices
    }
    private val characterMapper = CharacterMapper()
    private val comicMapper = ComicMapper()
    private val eventMapper = EventMapper()
    private val seriesMapper = SeriesMapper()
    private val fakeLocalData = FakeLocalData()

    fun searchComics(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchComics(limit, title).map { comicsDto ->
            comicMapper.map(comicsDto)
        }

    fun searchSeries(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchSeries(limit, title)


    fun searchCharacters(limit: Int? = null, title: String? = null) =
        marvelApiServices.fetchCharacters(limit, title)


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