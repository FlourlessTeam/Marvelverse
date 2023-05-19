package com.example.marvelverse.domain.mapper.comic

import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class ComicToComicEntityMapper  @Inject constructor() : Mapper<Comic, ComicEntity> {
    override fun map(input: Comic): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            characters = input.charactersUri,
            creators = input.creatorsUri,
            events = input.eventsUri,
            series = input.seriesUri,
            stories = input.storiesUri,
            description = input.description,
            pageCount = input.pageCount,
            resourceURI = input.resourceURI,
            thumbnail = input.imageUrl
        )
    }
}