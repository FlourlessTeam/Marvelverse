package com.example.marvelverse.domain.mapper.event

import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class EventSearchEntityToEventMapper @Inject constructor() : Mapper<EventSearchEntity, Event> {
    override fun map(input: EventSearchEntity): Event {
        return Event(
            id = input.id,
            title = input.title,
            charactersUri = input.characters,
            creatorsUri = input.creators,
            description = input.description,
            seriesUri = input.series,
            comicsUri = input.comics,
            storiesUri = input.stories,
            imageUrl = input.thumbnail
        )
    }
}

