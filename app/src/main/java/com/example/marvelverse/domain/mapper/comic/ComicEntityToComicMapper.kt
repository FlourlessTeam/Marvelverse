package com.example.marvelverse.domain.mapper.comic

import com.example.marvelverse.data.dataSources.local.entities.home.ComicEntity
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class ComicEntityToComicMapper  @Inject constructor() : Mapper<ComicEntity, Comic> {
    override fun map(input: ComicEntity): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            charactersUri = input.charactersUri,
            seriesUri = input.seriesUri,
            storiesUri = input.storiesUri,
            eventsUri = input.eventsUri,
            pageCount = input.pageCount,
            creatorsUri = input.creatorsUri,
            resourceURI = input.resourceURI,
            imageUrl = input.imageUrl
        )
    }
}
