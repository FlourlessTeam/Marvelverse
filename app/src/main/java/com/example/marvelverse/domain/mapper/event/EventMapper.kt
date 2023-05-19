package com.example.marvelverse.domain.mapper.event

import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.mapper.Mapper
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






