package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.domain.entities.Event

class EventMapper : Mapper<EventDto, Event> {
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

class EventSearchEntityToEventMapper : Mapper<EventSearchEntity, Event> {
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

class EventToEventSearchEntityMapper : Mapper<Event, EventSearchEntity> {
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