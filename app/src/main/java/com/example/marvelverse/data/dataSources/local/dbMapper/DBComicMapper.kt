package com.example.marvelverse.data.dataSources.local.dbMapper

import com.example.marvelverse.data.dataSources.remote.reponses.ComicDto
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.domain.mapper.Mapper

class DBComicMapper : Mapper<ComicDto, ComicEntity> {
    override fun map(input: ComicDto): ComicEntity {
        return ComicEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            resourceURI = input.resourceURI,
            pageCount = input.pageCount,
            series = input.series?.collectionURI,
            characters = input.characters?.collectionURI,
            creators = input.creators?.collectionURI,
            stories = input.stories?.collectionURI,
            events = input.events?.collectionURI,
            thumbnail = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}