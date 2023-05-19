package com.example.marvelverse.domain.mapper.comic

import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class ComicToComicSearchEntityMapper  @Inject constructor(): Mapper<Comic, ComicSearchEntity> {
    override fun map(input: Comic): ComicSearchEntity {
        return ComicSearchEntity(
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
