package com.example.marvelverse.domain.mapper.series

import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class SeriesMapper  @Inject constructor() : Mapper<SeriesDto, Series> {
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



