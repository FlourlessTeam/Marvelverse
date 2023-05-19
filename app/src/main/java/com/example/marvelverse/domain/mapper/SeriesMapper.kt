package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.home.SeriesEntity
import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.domain.entities.Series
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

class SeriesToSeriesEntityMapper  @Inject constructor() : Mapper<Series, SeriesEntity> {
    override fun map(input: Series): SeriesEntity {
        return SeriesEntity(
            input.id,
            input.title,
            input.description,
            input.resourceURI,
            input.comicsUri,
            input.charactersUri,
            input.creatorsUri,
            input.storiesUri,
            input.eventsUri,
            input.imageUrl
        )
    }
}

class SeriesEntityToSeriesMapper  @Inject constructor(): Mapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            input.id,
            input.title,
            input.description,
            input.resourceURI,
            input.comicsUri,
            input.charactersUri,
            input.creatorsUri,
            input.storiesUri,
            input.eventsUri,
            input.imageUrl
        )
    }
}