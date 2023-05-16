package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class SubList(
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("available")
    val available: Int? = null,
)
