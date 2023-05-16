package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class StoriesDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("comics")
    val comics: SubList? = null,
    @SerializedName("series")
    val series: SubList? = null,
    @SerializedName("events")
    val events: SubList? = null,
    @SerializedName("creators")
    val creators: SubList? = null,
    @SerializedName("characters")
    val characters: SubList? = null,
    @SerializedName("originalIssue")
    val originalIssue: SubList? = null

)
