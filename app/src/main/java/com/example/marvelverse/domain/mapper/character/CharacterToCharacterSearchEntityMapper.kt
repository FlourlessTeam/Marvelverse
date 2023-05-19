package com.example.marvelverse.domain.mapper.character

import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class CharacterToCharacterSearchEntityMapper @Inject constructor() : Mapper<Character, CharacterSearchEntity> {
    override fun map(input: Character): CharacterSearchEntity {
        return CharacterSearchEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            seriesUri = input.seriesUri,
            comicsUri = input.comicsUri,
            storiesUri = input.storiesUri,
            eventsUri = input.eventsUri,
            imageUrl = input.imageUrl
        )
    }
}