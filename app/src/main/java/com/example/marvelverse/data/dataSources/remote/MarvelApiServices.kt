package com.example.marvelverse.data.dataSources.remote

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Creator
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.wrappers.Response
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApiServices {

    @GET("comics")
    fun fetchComics(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Comic>>


    @GET("events")
    fun fetchEvents(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Event>>

    @GET("characters")
    fun fetchCharacters(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Character>>

    @GET("stories")
    fun fetchStories(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Story>>

    @GET("creators")
    fun fetchCreators(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Creator>>

    @GET("series")
    fun fetchSeries(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Series>>

    @GET
    fun fetchComicsByUrl(@Url url: String): Single<Response<Comic>>

    @GET
    fun fetchSeriesByUrl(@Url url: String): Single<Response<Series>>

    @GET
    fun fetchStoriesByUrl(@Url url: String): Single<Response<Story>>

    @GET
    fun fetchEventsByUrl(@Url url: String): Single<Response<Event>>

    @GET
    fun fetchCreatorsByUrl(@Url url: String): Single<Response<Creator>>

    @GET
    fun fetchCharactersByUrl(@Url url: String): Single<Response<Character>>
}
