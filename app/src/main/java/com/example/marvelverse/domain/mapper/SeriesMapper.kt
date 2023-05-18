package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.domain.entities.Series

class SeriesMapper:Mapper<SeriesDto,Series> {
    override fun map(input: SeriesDto): Series {
        return Series(
            id = input.id,
            title = input.title,
            description = input.description,
            resourceURI = input.resourceURI,
            comicsUri = input.comics?.collectionURI,
            creatorsUri = input.creators?.collectionURI,
            storiesUri = input.stories?.collectionURI,
            eventsUri = input.events?.collectionURI,
            charactersUri = input.characters?.collectionURI,
            imageUrl = input.thumbnail?.path + "." + input.thumbnail?.extension
        )
    }
}