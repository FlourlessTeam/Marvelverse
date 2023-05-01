package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail


data class Character(
    val id: Int,
    val name: String,
    val description: String?,
    val series: RelatedSeries,
    val comics: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
)
