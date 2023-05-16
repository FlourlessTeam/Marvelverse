package com.example.marvelverse.domain.mapper

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto

class CharacterMapper : Mapper<CharacterDto, Character> {
    override fun map(input: CharacterDto): Character {
        return Character(
            id = input.id,
            name = input.name,
            description = input.description,
            seriesUri = input.series?.collectionURI,
            comicsUri = input.comics?.collectionURI,
            storiesUri = input.stories?.collectionURI,
            eventsUri = input.events?.collectionURI,
            imageUrl = input.thumbnail?.path + "." + input.thumbnail?.extension
        )
    }

}