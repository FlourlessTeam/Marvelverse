package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class EventDto(
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
	@SerializedName("start")
	val start: String? = null,
	@SerializedName("end")
	val end: String? = null,
	@SerializedName("next")
	val next: SubList? = null,
	@SerializedName("previous")
	val previous: SubList? = null,
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
	@SerializedName("creators")
	val creators: SubList? = null,
	@SerializedName("characters")
	val characters: SubList? = null,
)