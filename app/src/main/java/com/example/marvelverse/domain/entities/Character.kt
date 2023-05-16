package com.example.marvelverse.domain.entities

import java.io.Serializable


data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
):Serializable
