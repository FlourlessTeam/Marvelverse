package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


data class Event(
    val id: Int,
    val title: String,
    val description: String?,
    val series: RelatedSeries,
    val comics: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val characters: InfoWrapper,
    val thumbnail: Thumbnail,
)
