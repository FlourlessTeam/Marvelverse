package com.example.marvelverse.di

import com.example.marvelverse.data.dataSources.local.FakeLocalData
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
import com.example.marvelverse.domain.mapper.SeriesMapper
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
		characterMapper: CharacterMapper,
		comicMapper: ComicMapper,
		eventMapper: EventMapper,
		seriesMapper: SeriesMapper,
		charToCharSearchEntityMapper: CharacterToCharacterSearchEntityMapper,
		charSearchEntityToCharMapper: CharacterSearchEntityToCharacterMapper,
		comicToComicSearchEntityMapper: ComicToComicSearchEntityMapper,
		comicSearchEntityToComicMapper: ComicSearchEntityToComicMapper,
		eventToEventSearchEntity: EventToEventSearchEntityMapper,
		eventSearchEntityToEventMapper: EventSearchEntityToEventMapper,
		fakeLocalData: FakeLocalData
	): MarvelRepository {
		return MarvelRepository(
			marvelApiServices,
			characterMapper,
			comicMapper,
			eventMapper,
			seriesMapper,
			charToCharSearchEntityMapper,
			charSearchEntityToCharMapper,
			comicToComicSearchEntityMapper,
			comicSearchEntityToComicMapper,
			eventToEventSearchEntity,
			eventSearchEntityToEventMapper,
			fakeLocalData
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