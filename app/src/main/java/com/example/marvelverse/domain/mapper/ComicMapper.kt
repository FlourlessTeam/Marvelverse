package com.example.marvelverse.domain.mapper

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