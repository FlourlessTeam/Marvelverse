package com.example.marvelverse.domain.mapper.character

import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class CharacterToCharacterEntityMapper @Inject constructor() : Mapper<Character, CharacterEntity> {
    override fun map(input: Character): CharacterEntity {
        return CharacterEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            series = input.seriesUri,
            comics = input.comicsUri,
            stories = input.storiesUri,
            events = input.eventsUri,
            thumbnail = input.imageUrl
        )
    }
}