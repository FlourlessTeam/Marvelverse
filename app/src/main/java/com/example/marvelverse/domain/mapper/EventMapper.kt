package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.home.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.domain.entities.Event
import javax.inject.Inject

class EventMapper  @Inject constructor(): Mapper<EventDto, Event> {
    override fun map(input: EventDto): Event {
        return Event(
            id = input.id,
            title = input.title,
            description = input.description,
            seriesUri = input.series?.collectionURI,
            comicsUri = input.comics?.collectionURI,
            creatorsUri = input.creators?.collectionURI,
            storiesUri = input.stories?.collectionURI,
            charactersUri = input.characters?.collectionURI,
            imageUrl = input.thumbnail?.path + "." + input.thumbnail?.extension
        )
    }
}

class EventSearchEntityToEventMapper @Inject constructor() : Mapper<EventSearchEntity, Event> {
    override fun map(input: EventSearchEntity): Event {
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

class EventToEventEntityMapper  @Inject constructor() : Mapper<Event, EventEntity> {
    override fun map(input: Event): EventEntity {
        return EventEntity(
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


