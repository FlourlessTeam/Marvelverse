package com.example.marvelverse.data.dataSources.local.dbMapper

import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import com.example.marvelverse.domain.mapper.Mapper

class DBSeriesMapper : Mapper<SeriesDto, SeriesEntity> {
    override fun map(input: SeriesDto): SeriesEntity {
        return SeriesEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            resourceURI = input.resourceURI,
            comics = input.comics?.collectionURI,
            characters = input.characters?.collectionURI,
            creators = input.creators?.collectionURI,
            stories = input.stories?.collectionURI,
            events = input.events?.collectionURI,
            thumbnail = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}