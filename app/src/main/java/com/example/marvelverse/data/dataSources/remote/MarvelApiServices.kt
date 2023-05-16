package com.example.marvelverse.data.dataSources.remote
import com.example.marvelverse.data.dataSources.remote.reponses.BaseResponse
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.data.dataSources.remote.reponses.ComicDto
import com.example.marvelverse.data.dataSources.remote.reponses.CreatorsDto
import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.data.dataSources.remote.reponses.StoriesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApiServices {

    @GET("comics")
    fun fetchComics(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<BaseResponse<ComicDto>>


    @GET("events")
    fun fetchEvents(
        @Query("limit") limit: Int?,
        @Query("nameStartsWith") title: String?
    ): Single<BaseResponse<EventDto>>

    @GET("characters")
    fun fetchCharacters(
        @Query("limit") limit: Int?,
        @Query("nameStartsWith") title: String?
    ): Single<BaseResponse<CharacterDto>>

    @GET("stories")
    fun fetchStories(
        @Query("limit") limit: Int?,
        //@Query("titleStartsWith") title: String?
    ): Single<BaseResponse<StoriesDto>>

    @GET("creators")
    fun fetchCreators(
        @Query("limit") limit: Int?,
        @Query("nameStartsWith") title: String?
    ): Single<BaseResponse<CreatorsDto>>

    @GET("series")
    fun fetchSeries(
        @Query("limit") limit: Int?,
        @Query("titleStartsWith") title: String?
    ): Single<BaseResponse<SeriesDto>>

    @GET
    fun fetchComicsByUrl(@Url url: String): Single<BaseResponse<ComicDto>>

    @GET
    fun fetchSeriesByUrl(@Url url: String): Single<BaseResponse<SeriesDto>>

    @GET
    fun fetchStoriesByUrl(@Url url: String): Single<BaseResponse<StoriesDto>>

    @GET
    fun fetchEventsByUrl(@Url url: String): Single<BaseResponse<EventDto>>

    @GET
    fun fetchCreatorsByUrl(@Url url: String): Single<BaseResponse<CreatorsDto>>

    @GET
    fun fetchCharactersByUrl(@Url url: String): Single<BaseResponse<CharacterDto>>
}
