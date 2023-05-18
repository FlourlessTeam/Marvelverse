package com.example.marvelverse.data.dataSources.local.dbMapper

import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.domain.mapper.Mapper

class DBEventMapper : Mapper<EventDto, EventEntity> {
    override fun map(input: EventDto): EventEntity {
        return EventEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            series = input.series?.collectionURI,
            comics = input.comics?.collectionURI,
            creators = input.creators?.collectionURI,
            stories = input.stories?.collectionURI,
            characters = input.characters?.collectionURI,
            thumbnail = "${input.thumbnail?.path}.${input.thumbnail?.extension}"

        )
    }
}