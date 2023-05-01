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

interface MarvelApiServices {

    @GET("comics")
    fun getComics(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Comic>>


    @GET("events")
    fun getEvents(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Event>>

    @GET("characters")
    fun getCharacters(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Character>>

    @GET("stories")
    fun getStories(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Story>>

    @GET("creators")
    fun getCreators(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Creator>>

    @GET("series")
    fun getSeries(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<Response<Series>>
}
