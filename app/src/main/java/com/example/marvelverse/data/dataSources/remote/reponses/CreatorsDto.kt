package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class CreatorsDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("middleName")
    val middleName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("suffix")
    val suffix: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null,
    @SerializedName("urls")
    val urls: List<Url>? = null,
    @SerializedName("comics")
    val comics: SubList? = null,
    @SerializedName("series")
    val series: SubList? = null,
    @SerializedName("stories")
    val stories: SubList? = null,
    @SerializedName("events")
    val events: SubList? = null,

)
