package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class Url(
	@SerializedName("type")
	val type: String? = null,
	@SerializedName("url")
	val url: String? = null,
)
