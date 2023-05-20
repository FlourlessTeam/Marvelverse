package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class SeriesDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("startYear")
    val start: Int? = null,
    @SerializedName("endYear")
    val end: Int? = null,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null,
    @SerializedName("comics")
    val comics: SubList? = null,
    @SerializedName("stories")
    val stories: SubList? = null,
    @SerializedName("events")
    val events: SubList? = null,
    @SerializedName("creators")
    val creators: SubList? = null,
    @SerializedName("characters")
    val characters: SubList? = null,
)
