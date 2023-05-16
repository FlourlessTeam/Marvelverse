package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class Date(
	@SerializedName("type")
	val type: String? = null,
	@SerializedName("date")
	val date: String? = null,
)
