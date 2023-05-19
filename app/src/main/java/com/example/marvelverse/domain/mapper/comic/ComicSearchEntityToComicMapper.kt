package com.example.marvelverse.domain.mapper.comic

import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class ComicSearchEntityToComicMapper  @Inject constructor(): Mapper<ComicSearchEntity, Comic> {
    override fun map(input: ComicSearchEntity): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            charactersUri = input.characters,
            seriesUri = input.series,
            storiesUri = input.stories,
            eventsUri = input.events,
            pageCount = input.pageCount,
            creatorsUri = input.creators,
            resourceURI = input.resourceURI,
            imageUrl = input.thumbnail
        )
    }
}