package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import java.io.Serializable

data class Story(
    val id: Int,
    val title: String,
    val resourceURI: String,
    val relatedComics: InfoWrapper,
    val relatedCharacters: InfoWrapper,
    val relatedCreators: InfoWrapper,
    val relatedCollectionSeries: RelatedCollectionSeries,
    val relatedEvents: InfoWrapper,
): Serializable
