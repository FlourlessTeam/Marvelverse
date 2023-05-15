package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class ComicDto(
	@SerializedName("id")
	val id: Int? = null,
	@SerializedName("digitalId")
	val digitalId: Int? = null,
	@SerializedName("title")
	val title: String? = null,
	@SerializedName("issueNumber")
	val issueNumber: Int? = null,
	@SerializedName("variantDescription")
	val variantDescription: String? = null,
	@SerializedName("description")
	val description: String? = null,
	@SerializedName("isbn")
	val isbn: String? = null,
	@SerializedName("upc")
	val upc: String? = null,
	@SerializedName("diamondCode")
	val diamondCode: String? = null,
	@SerializedName("ean")
	val ean: String? = null,
	@SerializedName("issn")
	val issn: String? = null,
	@SerializedName("format")
	val format: String? = null,
	@SerializedName("pageCount")
	val pageCount: Int? = null,
	@SerializedName("textObjects")
	val textObjects: List<Any>? = null,
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
	@SerializedName("variants")
	val variants: SubList? = null,
	@SerializedName("collections")
	val collections: SubList? = null,
	@SerializedName("collectedIssues")
	val collectedIssues: SubList? = null,
	@SerializedName("dates")
	val dates: List<Dates>? = null,
	@SerializedName("prices")
	val prices: List<Prices>? = null,
	@SerializedName("images")
	val images: SubList? = null,
	@SerializedName("creators")
	val creators: SubList? = null,
	@SerializedName("characters")
	val characters: SubList? = null,
)

