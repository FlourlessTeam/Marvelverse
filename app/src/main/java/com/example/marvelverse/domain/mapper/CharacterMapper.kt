package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.domain.entities.Character

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

class CharacterSearchEntityToCharacterMapper : Mapper<CharacterSearchEntity, Character> {
    override fun map(input: CharacterSearchEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            description = input.description,
            seriesUri = input.series,
            comicsUri = input.comics,
            storiesUri = input.stories,
            eventsUri = input.events,
            imageUrl = input.thumbnail
        )
    }
}

class CharacterToCharacterSearchEntityMapper : Mapper<Character, CharacterSearchEntity> {
    override fun map(input: Character): CharacterSearchEntity {
        return CharacterSearchEntity(
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
class CharacterEntityToCharacterMapper : Mapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            description = input.description,
            seriesUri = input.series,
            comicsUri = input.comics,
            storiesUri = input.stories,
            eventsUri = input.events,
            imageUrl = input.thumbnail
        )
    }
}

class CharacterToCharacterEntityMapper : Mapper<Character, CharacterEntity> {
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