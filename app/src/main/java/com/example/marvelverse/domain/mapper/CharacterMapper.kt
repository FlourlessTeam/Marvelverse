package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.home.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.domain.entities.Character
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

class CharacterSearchEntityToCharacterMapper @Inject constructor() : Mapper<CharacterSearchEntity, Character> {
    override fun map(input: CharacterSearchEntity): Character {
        return Character(
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
class CharacterEntityToCharacterMapper @Inject constructor() : Mapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
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

class CharacterToCharacterEntityMapper @Inject constructor() : Mapper<Character, CharacterEntity> {
    override fun map(input: Character): CharacterEntity {
        return CharacterEntity(
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