package com.example.marvelverse.domain.entities.main

import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

data class Series(
    val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val comics: InfoWrapper,
    val characters: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
)
