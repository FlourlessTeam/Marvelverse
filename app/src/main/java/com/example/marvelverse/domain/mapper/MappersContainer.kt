package com.example.marvelverse.domain.mapper

import javax.inject.Inject

class MappersContainer @Inject constructor(
    val characterMapper: CharacterMapper,
    val comicMapper: ComicMapper,
    val eventMapper: EventMapper,
    val seriesMapper: SeriesMapper,
    val charToCharSearchEntityMapper: CharacterToCharacterSearchEntityMapper,
    val charSearchEntityToCharMapper: CharacterSearchEntityToCharacterMapper,
    val comicToComicSearchEntityMapper: ComicToComicSearchEntityMapper,
    val comicSearchEntityToComicMapper: ComicSearchEntityToComicMapper,
    val eventToEventSearchEntity: EventToEventSearchEntityMapper,
    val eventSearchEntityToEventMapper: EventSearchEntityToEventMapper
)