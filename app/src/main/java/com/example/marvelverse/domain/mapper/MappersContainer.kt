package com.example.marvelverse.domain.mapper

import com.example.marvelverse.domain.mapper.character.CharacterEntityToCharacterMapper
import com.example.marvelverse.domain.mapper.character.CharacterMapper
import com.example.marvelverse.domain.mapper.character.CharacterSearchEntityToCharacterMapper
import com.example.marvelverse.domain.mapper.character.CharacterToCharacterEntityMapper
import com.example.marvelverse.domain.mapper.character.CharacterToCharacterSearchEntityMapper
import com.example.marvelverse.domain.mapper.comic.ComicEntityToComicMapper
import com.example.marvelverse.domain.mapper.comic.ComicMapper
import com.example.marvelverse.domain.mapper.comic.ComicSearchEntityToComicMapper
import com.example.marvelverse.domain.mapper.comic.ComicToComicEntityMapper
import com.example.marvelverse.domain.mapper.comic.ComicToComicSearchEntityMapper
import com.example.marvelverse.domain.mapper.event.EventEntityToEventMapper
import com.example.marvelverse.domain.mapper.event.EventMapper
import com.example.marvelverse.domain.mapper.event.EventSearchEntityToEventMapper
import com.example.marvelverse.domain.mapper.event.EventToEventEntityMapper
import com.example.marvelverse.domain.mapper.event.EventToEventSearchEntityMapper
import com.example.marvelverse.domain.mapper.keyword.KeywordEntityToKeywordMapper
import com.example.marvelverse.domain.mapper.keyword.KeywordToKeywordEntityMapper
import com.example.marvelverse.domain.mapper.series.SeriesEntityToSeriesMapper
import com.example.marvelverse.domain.mapper.series.SeriesMapper
import com.example.marvelverse.domain.mapper.series.SeriesToSeriesEntityMapper
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
    val keywordToKeywordEntityMapper: KeywordToKeywordEntityMapper
)