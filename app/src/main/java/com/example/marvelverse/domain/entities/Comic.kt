package com.example.marvelverse.domain.entities

import java.io.Serializable


data class Comic(
    val id: Int?,
    val title: String?,
    val description:String?,
    val resourceURI: String?,
    val pageCount:Int?,
    val seriesUri: String?,
    val charactersUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
): Serializable

