package com.example.marvelverse.data.repositories

import com.example.marvelverse.data.dataSources.remote.RetrofitClient


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


}