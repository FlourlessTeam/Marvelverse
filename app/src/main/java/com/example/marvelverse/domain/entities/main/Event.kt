package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable



data class Event(
    val id: Int,
    val title: String,
    val description: String?,
    val series: RelatedCollectionSeries,
    val comics: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val characters: InfoWrapper,
    val thumbnail: Thumbnail,
) : Serializable
