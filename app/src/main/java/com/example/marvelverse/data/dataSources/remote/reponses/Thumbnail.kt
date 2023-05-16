package com.example.marvelverse.data.dataSources.remote.reponses

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("extension")
    val extension: String? = null,
    )
