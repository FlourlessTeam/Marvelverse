package com.example.marvelverse.domain.mapper.event

import com.example.marvelverse.data.dataSources.local.entities.home.EventEntity
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class EventEntityToEventMapper  @Inject constructor() : Mapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {
        return Event(
            id = input.id,
            title = input.title,
            charactersUri = input.charactersUri,
            creatorsUri = input.creatorsUri,
            description = input.description,
            seriesUri = input.seriesUri,
            comicsUri = input.comicsUri,
            storiesUri = input.storiesUri,
            imageUrl = input.imageUrl
        )
    }
}
