package com.example.marvelverse.domain.entities

import java.io.Serializable


data class Event(
    val id: Int?,
    val title: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val charactersUri: String?,
    val imageUrl: String?,
) : Serializable
