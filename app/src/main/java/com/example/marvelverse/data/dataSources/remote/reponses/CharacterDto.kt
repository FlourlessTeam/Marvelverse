package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class CharacterDto(
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("name")
	val name: String? = null,
	@SerializedName("description")
	val description: String? = null,
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
	@SerializedName("resourceURI")
	val resourceURI: String? = null,
)