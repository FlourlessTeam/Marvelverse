package com.example.marvelverse.domain.mapper.character

import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class CharacterMapper  @Inject constructor(): Mapper<CharacterDto, Character> {
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




