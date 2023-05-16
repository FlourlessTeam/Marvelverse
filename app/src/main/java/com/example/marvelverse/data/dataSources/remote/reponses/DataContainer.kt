package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class DataContainer<T>(
	@SerializedName("offset")
	val offset: Int? = null,
	@SerializedName("limit")
	val limit: Int? = null,
	@SerializedName("total")
	val total: Int? = null,
	@SerializedName("count")
	val count: Int? = null,
	@SerializedName("results")
	val results: List<T>? = null,
)
