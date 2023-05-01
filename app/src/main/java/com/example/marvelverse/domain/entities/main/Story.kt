package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedSeries

data class Story(
    val id: Int,
    val title: String,
    val resourceURI: String,
    val relatedComics: InfoWrapper,
    val relatedCharacters: InfoWrapper,
    val relatedCreators: InfoWrapper,
    val relatedSeries: RelatedSeries,
    val relatedEvents: InfoWrapper,
)
