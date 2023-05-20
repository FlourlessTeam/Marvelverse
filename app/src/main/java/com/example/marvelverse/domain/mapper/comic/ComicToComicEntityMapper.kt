package com.example.marvelverse.domain.mapper.comic

import com.example.marvelverse.data.dataSources.local.entities.home.ComicEntity
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class ComicToComicEntityMapper  @Inject constructor() : Mapper<Comic, ComicEntity> {
    override fun map(input: Comic): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            charactersUri = input.charactersUri,
            creatorsUri = input.creatorsUri,
            eventsUri = input.eventsUri,
            seriesUri = input.seriesUri,
            storiesUri = input.storiesUri,
            description = input.description,
            pageCount = input.pageCount,
            resourceURI = input.resourceURI,
            imageUrl = input.imageUrl
        )
    }
}