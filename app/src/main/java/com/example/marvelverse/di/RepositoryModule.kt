package com.example.marvelverse.di

import com.example.marvelverse.data.dataSources.local.FakeLocalData
import com.example.marvelverse.data.dataSources.local.MarvelDatabase
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.mapper.CharacterMapper
import com.example.marvelverse.domain.mapper.CharacterSearchEntityToCharacterMapper
import com.example.marvelverse.domain.mapper.CharacterToCharacterSearchEntityMapper
import com.example.marvelverse.domain.mapper.ComicMapper
import com.example.marvelverse.domain.mapper.ComicSearchEntityToComicMapper
import com.example.marvelverse.domain.mapper.ComicToComicSearchEntityMapper
import com.example.marvelverse.domain.mapper.EventMapper
import com.example.marvelverse.domain.mapper.EventSearchEntityToEventMapper
import com.example.marvelverse.domain.mapper.EventToEventSearchEntityMapper
import com.example.marvelverse.domain.mapper.MappersContainer
import com.example.marvelverse.domain.mapper.SeriesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

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
    fun provideComicToComicSearchEntityMapper(): ComicToComicSearchEntityMapper {
        return ComicToComicSearchEntityMapper()
    }

    @Provides
    fun provideComicSearchEntityToComicMapper(): ComicSearchEntityToComicMapper {
        return ComicSearchEntityToComicMapper()
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