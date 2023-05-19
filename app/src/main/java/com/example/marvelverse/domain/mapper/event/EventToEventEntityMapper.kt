package com.example.marvelverse.domain.mapper.event

import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class EventToEventEntityMapper  @Inject constructor() : Mapper<Event, EventEntity> {
    override fun map(input: Event): EventEntity {
        return EventEntity(
            id = input.id,
            title = input.title,
            creators = input.creatorsUri,
            description = input.description,
            characters = input.charactersUri,
            series = input.seriesUri,
            comics = input.comicsUri,
            stories = input.storiesUri,
            thumbnail = input.imageUrl
        )
    }
}