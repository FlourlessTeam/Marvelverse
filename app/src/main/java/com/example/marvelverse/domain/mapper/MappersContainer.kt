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
    val eventSearchEntityToEventMapper: EventSearchEntityToEventMapper,
    val characterEntityToCharacterMapper: CharacterEntityToCharacterMapper,
    val characterToCharacterEntityMapper: CharacterToCharacterEntityMapper,
    val comicEntityToComicMapper: ComicEntityToComicMapper,
    val comicToComicEntityMapper: ComicToComicEntityMapper,
    val seriesToSeriesEntityMapper: SeriesToSeriesEntityMapper,
    val seriesEntityToSeriesMapper: SeriesEntityToSeriesMapper,
    val eventEntityToEventMapper: EventEntityToEventMapper,
    val eventToEventEntityMapper: EventToEventEntityMapper,
    val keywordEntityToKeywordMapper: KeywordEntityToKeywordMapper,
    val keywordToKeywordEntityMapper: KeywordToKeywordEntityMapper,
)