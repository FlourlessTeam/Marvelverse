package com.example.marvelverse.domain.mapper.event

import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class EventToEventSearchEntityMapper  @Inject constructor(): Mapper<Event, EventSearchEntity> {
    override fun map(input: Event): EventSearchEntity {
        return EventSearchEntity(
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
