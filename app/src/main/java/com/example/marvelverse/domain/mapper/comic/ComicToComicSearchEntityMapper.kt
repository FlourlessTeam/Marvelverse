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
