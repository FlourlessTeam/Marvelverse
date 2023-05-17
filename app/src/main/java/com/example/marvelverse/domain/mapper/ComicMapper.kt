package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.data.dataSources.remote.reponses.ComicDto
import com.example.marvelverse.domain.entities.Comic

class ComicMapper: Mapper<ComicDto, Comic> {
    override fun map(input: ComicDto): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            resourceURI = input.resourceURI,
            pageCount = input.pageCount,
            seriesUri = input.series?.collectionURI,
            charactersUri = input.characters?.collectionURI,
            creatorsUri = input.creators?.collectionURI,
            storiesUri = input.stories?.collectionURI,
            eventsUri = input.events?.collectionURI,
            imageUrl = input.thumbnail?.path + "." + input.thumbnail?.extension
        )
    }
}
class ComicSearchEntityToComicMapper : Mapper<ComicSearchEntity, Comic> {
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

class ComicToComicSearchEntityMapper : Mapper<Comic, ComicSearchEntity> {
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