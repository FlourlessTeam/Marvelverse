package com.example.marvelverse.domain.entities

import java.io.Serializable

data class Series(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val comicsUri: String?,
    val charactersUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
) : Serializable
