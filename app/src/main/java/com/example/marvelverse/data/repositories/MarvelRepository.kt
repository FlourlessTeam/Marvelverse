package com.example.marvelverse.data.repositories


import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.domain.entities.About
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.SearchKeyword
import com.example.marvelverse.domain.entities.Series
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun searchCacheCharacters(limit: Int? = null, name: String): Single<List<Character>>
    fun searchCharacters(limit: Int? = null, title: String? = null): Single<List<Character>>
    fun searchCachedComics(limit: Int? = null, title: String): Single<List<Comic>>
    fun searchComics(limit: Int? = null, title: String? = null): Single<List<Comic>>
    fun searchCachedEvents(limit: Int? = null, title: String): Single<List<Event>>
    fun searchEvents(limit: Int? = null, title: String? = null): Single<List<Event>>
    fun searchSeries(limit: Int? = null, title: String? = null): Single<List<Series>>
    fun saveKeyword(keyword: SearchKeyword): Completable
    fun getSearchKeywords(): Single<List<SearchKeyword>>
    fun getComicsByUrl(url: String): Single<List<Comic>>
    fun getSeriesByUrl(url: String): Single<List<Series>>
    fun getCharactersByUrl(url: String): Single<List<Character>>
    fun getEventsByUrl(url: String): Single<List<Event>>
    fun getHomeItems(): Single<List<HomeItem>>
    fun getItems(): List<About>
}
