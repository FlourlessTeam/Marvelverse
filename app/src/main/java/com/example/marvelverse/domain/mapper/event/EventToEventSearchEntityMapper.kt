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
            creatorsUri = input.creatorsUri,
            description = input.description,
            charactersUri = input.charactersUri,
            seriesUri = input.seriesUri,
            comicsUri = input.comicsUri,
            storiesUri = input.storiesUri,
            imageUrl = input.imageUrl
        )
    }
}
