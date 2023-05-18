package com.example.marvelverse.di

import com.example.marvelverse.data.dataSources.local.FakeLocalData
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.mapper.CharacterEntityToCharacterMapper
import com.example.marvelverse.domain.mapper.CharacterMapper
import com.example.marvelverse.domain.mapper.CharacterSearchEntityToCharacterMapper
import com.example.marvelverse.domain.mapper.CharacterToCharacterEntityMapper
import com.example.marvelverse.domain.mapper.CharacterToCharacterSearchEntityMapper
import com.example.marvelverse.domain.mapper.ComicEntityToComicMapper
import com.example.marvelverse.domain.mapper.ComicMapper
import com.example.marvelverse.domain.mapper.ComicSearchEntityToComicMapper
import com.example.marvelverse.domain.mapper.ComicToComicEntityMapper
import com.example.marvelverse.domain.mapper.ComicToComicSearchEntityMapper
import com.example.marvelverse.domain.mapper.EventEntityToEventMapper
import com.example.marvelverse.domain.mapper.EventMapper
import com.example.marvelverse.domain.mapper.EventSearchEntityToEventMapper
import com.example.marvelverse.domain.mapper.EventToEventEntityMapper
import com.example.marvelverse.domain.mapper.EventToEventSearchEntityMapper
import com.example.marvelverse.domain.mapper.KeywordEntityToKeywordMapper
import com.example.marvelverse.domain.mapper.KeywordToKeywordEntityMapper
import com.example.marvelverse.domain.mapper.MappersContainer
import com.example.marvelverse.domain.mapper.SeriesEntityToSeriesMapper
import com.example.marvelverse.domain.mapper.SeriesMapper
import com.example.marvelverse.domain.mapper.SeriesToSeriesEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMarvelRepository(
        marvelApiServices: MarvelApiServices,
        dataMapper: MappersContainer,
        fakeLocalData: FakeLocalData,
        homeDao: HomeDao,
        searchDao: SearchDao,
    ): MarvelRepository {
        return MarvelRepository(
            marvelApiServices,
            dataMapper,
            fakeLocalData,
            homeDao,
            searchDao
        )
    }

    @Provides
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    fun provideCharToCharSearchEntityMapper(): CharacterToCharacterSearchEntityMapper {
        return CharacterToCharacterSearchEntityMapper()
    }

    @Provides
    fun provideCharSearchEntityToCharMapper(): CharacterSearchEntityToCharacterMapper {
        return CharacterSearchEntityToCharacterMapper()
    }

    @Provides
    fun provideCharToCharEntityMapper(): CharacterToCharacterEntityMapper {
        return CharacterToCharacterEntityMapper()
    }

    @Provides
    fun provideCharEntityToCharMapper(): CharacterEntityToCharacterMapper {
        return CharacterEntityToCharacterMapper()
    }

    @Provides
    fun provideComicToComicSearchEntityMapper(): ComicToComicSearchEntityMapper {
        return ComicToComicSearchEntityMapper()
    }

    @Provides
    fun provideComicSearchEntityToComicMapper(): ComicSearchEntityToComicMapper {
        return ComicSearchEntityToComicMapper()
    }

    @Provides
    fun provideComicToComicEntityMapper(): ComicToComicEntityMapper {
        return ComicToComicEntityMapper()
    }

    @Provides
    fun provideComicEntityToComicMapper(): ComicEntityToComicMapper {
        return ComicEntityToComicMapper()
    }

    @Provides
    fun provideEventToEventSearchEntityMapper(): EventToEventSearchEntityMapper {
        return EventToEventSearchEntityMapper()
    }

    @Provides
    fun provideEventSearchEntityToEventMapper(): EventSearchEntityToEventMapper {
        return EventSearchEntityToEventMapper()
    }

    @Provides
    fun provideEventToEventEntityMapper(): EventToEventEntityMapper {
        return EventToEventEntityMapper()
    }

    @Provides
    fun provideSeriesEntityToSeriesMapper(): SeriesEntityToSeriesMapper {
        return SeriesEntityToSeriesMapper()
    }

    @Provides
    fun provideSeriesToSeriesEntityMapper(): SeriesToSeriesEntityMapper {
        return SeriesToSeriesEntityMapper()
    }

    @Provides
    fun provideEventEntityToEventMapper(): EventEntityToEventMapper {
        return EventEntityToEventMapper()
    }

    @Provides
    fun provideKeywordEntityToKeywordMapper() = KeywordEntityToKeywordMapper()

    @Provides
    fun provideKeywordToKeywordEntityMapper() = KeywordToKeywordEntityMapper()


    @Provides
    fun provideComicMapper(): ComicMapper {
        return ComicMapper()
    }

    @Provides
    fun provideEventMapper(): EventMapper {
        return EventMapper()
    }

    @Provides
    fun provideSeriesMapper(): SeriesMapper {
        return SeriesMapper()
    }

    @Provides
    fun provideFakeLocalData(): FakeLocalData {
        return FakeLocalData()
    }

}