package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

data class Creator(
    val id: Int,
    val fullName: String,
    val relatedSeries: RelatedSeries,
    val comics: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
)
