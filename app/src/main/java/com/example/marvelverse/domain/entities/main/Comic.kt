package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable


data class Comic(
    val id: Int,
    val title: String,
    val description:String?,
    val resourceURI: String,
    val pageCount:Int,
    val series: RelatedCollectionSeries,
    val characters: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
): Serializable

