package com.example.marvelverse.data.repositories

import com.example.marvelverse.data.dataSources.remote.RetrofitClient
import com.example.marvelverse.data.local.FakeLocalData
import com.example.marvelverse.domain.mapper.CharacterMapper
import com.example.marvelverse.domain.mapper.ComicMapper
import com.example.marvelverse.domain.mapper.EventMapper
import com.example.marvelverse.domain.mapper.SeriesMapper


object MarvelRepository {
	private val marvelApiServices by lazy {
		RetrofitClient.marvelApiServices
	}
	private val characterMapper = CharacterMapper()
	private val comicMapper = ComicMapper()
	private val eventMapper = EventMapper()
	private val seriesMapper = SeriesMapper()
	private val fakeLocalData = FakeLocalData()

	fun searchComics(limit: Int? = null, title: String? = null) =
		marvelApiServices.fetchComics(limit, title).map { baseResponse ->
			baseResponse.data?.results?.map { comicDto ->
				comicMapper.map(comicDto)
			} ?: emptyList()
		}


	fun searchSeries(limit: Int? = null, title: String? = null) =
		marvelApiServices.fetchSeries(limit, title).map { baseResponse ->
			baseResponse.data?.results?.map { seriesDto ->
				seriesMapper.map(seriesDto)
			} ?: emptyList()
		}


	fun searchCharacters(limit: Int? = null, title: String? = null) =
		marvelApiServices.fetchCharacters(limit, title).map { baseResponse ->
			baseResponse.data?.results?.map { characterDto ->
				characterMapper.map(characterDto)
			} ?: emptyList()
		}


	fun searchEvents(limit: Int? = null, title: String? = null) =
		marvelApiServices.fetchEvents(limit, title).map { baseResponse ->
			baseResponse.data?.results?.map { eventDto ->
				eventMapper.map(eventDto)
			} ?: emptyList()
		}

	fun getComicsByUrl(url: String) =
		marvelApiServices.fetchComicsByUrl(url).map { baseResponse ->
			baseResponse.data?.results?.map { comicDto ->
				comicMapper.map(comicDto)
			} ?: emptyList()
		}

	fun getSeriesByUrl(url: String) =
		marvelApiServices.fetchSeriesByUrl(url).map { baseResponse ->
			baseResponse.data?.results?.map { seriesDto ->
				seriesMapper.map(seriesDto)
			} ?: emptyList()
		}

	fun getCharactersByUrl(url: String) =
		marvelApiServices.fetchCharactersByUrl(url).map { baseResponse ->
			baseResponse.data?.results?.map { characterDto ->
				characterMapper.map(characterDto)
			} ?: emptyList()
		}

	fun getEventsByUrl(url: String) =
		marvelApiServices.fetchEventsByUrl(url).map { baseResponse ->
			baseResponse.data?.results?.map { eventDto ->
				eventMapper.map(eventDto)
			} ?: emptyList()
		}


	fun getRandomCharacters() =
		marvelApiServices.fetchCharacters(80, null).map { baseResponse ->
			baseResponse.data?.results?.shuffled()?.take(20)?.map { characterDto ->
				characterMapper.map(characterDto)
			} ?: emptyList()
		}

	fun getRandomComics() =
		marvelApiServices.fetchComics(50, null).map { baseResponse ->
			baseResponse.data?.results?.shuffled()?.take(10)?.map { comicDto ->
				comicMapper.map(comicDto)
			} ?: emptyList()
		}


	fun getRandomSeries() =
		marvelApiServices.fetchSeries(50, null).map { baseResponse ->
			baseResponse.data?.results?.shuffled()?.take(10)?.map { seriesDto ->
				seriesMapper.map(seriesDto)
			} ?: emptyList()
		}


	fun getRandomEvents() =
		marvelApiServices.fetchEvents(50, null).map { baseResponse ->
			baseResponse.data?.results?.shuffled()?.take(10)?.map { eventDto ->
				eventMapper.map(eventDto)
			} ?: emptyList()
		}

	fun getItems() = fakeLocalData.getAboutItems()
}