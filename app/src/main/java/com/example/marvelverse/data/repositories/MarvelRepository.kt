package com.example.marvelverse.data.repositories

import com.example.marvelverse.data.dataSources.remote.RetrofitClient


object MarvelRepository {
    private val marvelApiServices by lazy {
        RetrofitClient.marvelApiServices
    }

    fun searchComics(limit: Int? = null, title: String? = null) =
        marvelApiServices.getComics(limit, title).map { it.data.results }

    fun searchSeries(limit: Int? = null, title: String? = null) =
        marvelApiServices.getSeries(limit, title).map { it.data.results }


    fun searchCharacters(limit: Int? = null, title: String? = null) =
        marvelApiServices.getCharacters(limit, title).map { it.data.results }


    fun searchEvents(limit: Int? = null, title: String? = null) =
        marvelApiServices.getEvents(limit, title).map { it.data.results }


    fun searchStories(limit: Int? = null, title: String? = null) =
        marvelApiServices.getStories(limit, title).map { it.data.results }


    fun searchCreators(limit: Int? = null, title: String? = null) =
        marvelApiServices.getCreators(limit, title).map { it.data.results }

}