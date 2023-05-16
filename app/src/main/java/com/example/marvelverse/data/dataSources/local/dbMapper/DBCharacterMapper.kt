package com.example.marvelverse.data.dataSources.local.dbMapper

import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.domain.mapper.Mapper

class DBCharacterMapper : Mapper<CharacterDto, CharacterEntity> {
    override fun map(input: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            series = input.series?.collectionURI,
            comics = input.comics?.collectionURI,
            stories = input.stories?.collectionURI,
            events = input.events?.collectionURI,
            thumbnail = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )

    }
}