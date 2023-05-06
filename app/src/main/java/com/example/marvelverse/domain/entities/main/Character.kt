package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable


data class Character(
    val id: Int,
    val name: String,
    val description: String?,
    val series: RelatedCollectionSeries,
    val comics: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
):Serializable
