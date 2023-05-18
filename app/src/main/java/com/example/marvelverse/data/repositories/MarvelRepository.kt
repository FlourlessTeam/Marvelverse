package com.example.marvelverse.data.repositories

import com.example.marvelverse.app.ui.home.HomeItem
import com.example.marvelverse.data.dataSources.local.FakeLocalData
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.data.dataSources.remote.reponses.ComicDto
import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.domain.mapper.MappersContainer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class MarvelRepository @Inject constructor(
    private val marvelApiServices: MarvelApiServices,
    private val dataMapper: MappersContainer,
    private val fakeLocalData: FakeLocalData,
    private val homeDao: HomeDao,
    private val searchDao: SearchDao,
) {

    fun searchCharacters(limit: Int? = null, title: String? = null): Single<List<Character>> {
        return marvelApiServices.fetchCharacters(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { characterDto ->
                characterDto.mapToCharacter()
            } ?: emptyList()
        }
    }

    fun searchCacheCharacters(limit: Int? = null, name: String): Single<List<Character>> {
        val savedCharacters = getCachedCharacters(name)

        return if (savedCharacters.isNotEmpty()) {
            Single.just(savedCharacters)
        } else {
            try {
                val response = searchCharacters(limit, name)
                cacheCharacters(response)
                response
            } catch (e: Exception) {
                Single.just(emptyList())
            }
        }
    }

    private fun getCachedCharacters(name: String): List<Character> {
        val savedEntities = searchDao.getAllCharacters(name).blockingGet()
        return savedEntities.map { characterSearchEntity ->
            characterSearchEntity.mapToCharacter()
        }
    }

    private fun cacheCharacters(characters: Single<List<Character>>) {
        val cachedResponse = characters.blockingGet().map { character ->
            character.mapToCharacterSearchEntity()
        }
        searchDao.insertCharacters(cachedResponse)
    }

    fun searchComics(limit: Int? = null, title: String? = null): Single<List<Comic>> {
        return marvelApiServices.fetchComics(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { comicDto ->
                comicDto.mapToComic()
            } ?: emptyList()
        }
    }

    fun searchCachedComics(limit: Int? = null, title: String): Single<List<Comic>> {
        val savedComics = getCachedComics(title)

        return if (savedComics.isNotEmpty()) {
            Single.just(savedComics)
        } else {
            try {
                val response = searchComics(limit, title)
                cacheComics(response)
                response
            } catch (e: Exception) {
                Single.just(emptyList())
            }
        }
    }

    private fun getCachedComics(title: String): List<Comic> {
        val savedEntities = searchDao.getAllComics(title).blockingGet()
        return savedEntities.map { comicSearchEntity -> comicSearchEntity.mapToComic() }
    }

    private fun cacheComics(comics: Single<List<Comic>>) {
        val cachedResponse = comics.blockingGet().map { comic -> comic.mapToComicSearchEntity() }
        searchDao.insertComics(cachedResponse)
    }

    fun searchEvents(limit: Int? = null, title: String? = null): Single<List<Event>> {
        return marvelApiServices.fetchEvents(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { eventDto ->
                eventDto.mapToEvent()
            } ?: emptyList()
        }
    }

    fun searchCachedEvents(limit: Int? = null, title: String): Single<List<Event>> {
        val savedEvents = getCachedEvents(title)

        return if (savedEvents.isNotEmpty()) {
            Single.just(savedEvents)
        } else {
            try {
                val response = searchEvents(limit, title)
                cacheEvents(response)
                response
            } catch (e: Exception) {
                Single.just(emptyList())
            }
        }
    }

    private fun getCachedEvents(title: String): List<Event> {
        val savedEntities = searchDao.getAllEvents(title).blockingGet()
        return savedEntities.map { eventSearchEntity -> eventSearchEntity.mapToEvent() }
    }

    private fun cacheEvents(events: Single<List<Event>>) {
        val cachedResponse = events.blockingGet().map { event -> event.mapToEventSearchEntity() }
        searchDao.insertEvents(cachedResponse)
    }


    fun searchSeries(limit: Int? = null, title: String? = null): Single<List<Series>> {
        return marvelApiServices.fetchSeries(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { seriesDto ->
                seriesDto.mapToSeries()
            } ?: emptyList()
        }
    }

    fun getComicsByUrl(url: String): Single<List<Comic>> {
        return marvelApiServices.fetchComicsByUrl(url).map { baseResponse ->
            baseResponse.data?.results?.map { comicDto ->
                comicDto.mapToComic()
            } ?: emptyList()
        }
    }

    fun getSeriesByUrl(url: String): Single<List<Series>> {
        return marvelApiServices.fetchSeriesByUrl(url).map { baseResponse ->
            baseResponse.data?.results?.map { seriesDto ->
                seriesDto.mapToSeries()
            } ?: emptyList()
        }
    }

    fun getCharactersByUrl(url: String): Single<List<Character>> {
        return marvelApiServices.fetchCharactersByUrl(url).map { baseResponse ->
            baseResponse.data?.results?.map { characterDto ->
                characterDto.mapToCharacter()
            } ?: emptyList()
        }
    }

    fun getEventsByUrl(url: String): Single<List<Event>> {
        return marvelApiServices.fetchEventsByUrl(url).map { baseResponse ->
            baseResponse.data?.results?.map { eventDto ->
                eventDto.mapToEvent()
            } ?: emptyList()
        }
    }

    fun getRandomCharacters(): Single<List<Character>> {
        return marvelApiServices.fetchCharacters(80, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(20)?.map { characterDto ->
                characterDto.mapToCharacter()
            } ?: emptyList()
        }
    }

    fun getRandomComics(): Single<List<Comic>> {
        return marvelApiServices.fetchComics(50, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(10)?.map { comicDto ->
                comicDto.mapToComic()
            } ?: emptyList()
        }
    }

    fun getRandomSeries(): Single<List<Series>> {
        return marvelApiServices.fetchSeries(50, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(10)?.map { seriesDto ->
                seriesDto.mapToSeries()
            } ?: emptyList()
        }
    }

    fun getRandomEvents(): Single<List<Event>> {
        return marvelApiServices.fetchEvents(50, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(10)?.map { eventDto ->
                eventDto.mapToEvent()
            } ?: emptyList()
        }
    }

    fun getHomeItems(): Single<List<HomeItem>> {
        return Single.zip(
            getRandomCharacters(), getRandomComics(), getRandomSeries(), getRandomEvents()
        ) { characters, comics, series, events ->
            homeDao.insertCharacters(characters.map { it.mapToCharacterEntity() })
            homeDao.insertComics(comics.map { it.mapToComicEntity() })
            homeDao.insertSeries(series.map { it.mapToSeriesEntity() })
            homeDao.insertEvents(events.map { it.mapToEventEntity() })
            listOf(
                HomeItem.CharactersItem(characters),
                HomeItem.ComicsItem(comics),
                HomeItem.EventsItem(events),
                HomeItem.SeriesItem(series)
            )
        }.onErrorReturn {
            val chars = homeDao.getAllCharacters().map { it.map { it.mapToChar() } }.blockingGet()
            val comics = homeDao.getAllComics().map { it.map { it.mapToComic() } }.blockingGet()
            val events = homeDao.getAllEvents().map { it.map { it.mapToEvent() } }.blockingGet()
            val series = homeDao.getAllSeries().map { it.map { it.mapToSeries() } }.blockingGet()
            if (chars.isEmpty() &&
                comics.isEmpty() &&
                events.isEmpty() &&
                series.isEmpty()
            ) {
                throw it
            } else {
                listOf(
                    HomeItem.CharactersItem(chars),
                    HomeItem.ComicsItem(comics),
                    HomeItem.EventsItem(events),
                    HomeItem.SeriesItem(series)
                )
            }
        }
    }

    fun getItems() = fakeLocalData.getAboutItems()
    private fun ComicDto.mapToComic(): Comic = dataMapper.comicMapper.map(this)
    private fun SeriesDto.mapToSeries(): Series = dataMapper.seriesMapper.map(this)
    private fun CharacterDto.mapToCharacter(): Character = dataMapper.characterMapper.map(this)
    private fun EventDto.mapToEvent(): Event = dataMapper.eventMapper.map(this)

    private fun Character.mapToCharacterSearchEntity(): CharacterSearchEntity =
        dataMapper.charToCharSearchEntityMapper.map(this)

    private fun Character.mapToCharacterEntity(): CharacterEntity =
        dataMapper.characterToCharacterEntityMapper.map(this)

    private fun Comic.mapToComicSearchEntity(): ComicSearchEntity =
        dataMapper.comicToComicSearchEntityMapper.map(this)

    private fun Comic.mapToComicEntity(): ComicEntity =
        dataMapper.comicToComicEntityMapper.map(this)

    private fun Event.mapToEventSearchEntity(): EventSearchEntity =
        dataMapper.eventToEventSearchEntity.map(this)

    private fun CharacterSearchEntity.mapToCharacter(): Character =
        dataMapper.charSearchEntityToCharMapper.map(this)

    private fun ComicSearchEntity.mapToComic(): Comic =
        dataMapper.comicSearchEntityToComicMapper.map(this)

    private fun EventSearchEntity.mapToEvent(): Event =
        dataMapper.eventSearchEntityToEventMapper.map(this)

    private fun CharacterEntity.mapToChar() = dataMapper.characterEntityToCharacterMapper.map(this)
    private fun ComicEntity.mapToComic() = dataMapper.comicEntityToComicMapper.map(this)
    private fun SeriesEntity.mapToSeries() = dataMapper.seriesEntityToSeriesMapper.map(this)
    private fun EventEntity.mapToEvent() = dataMapper.eventEntityToEventMapper.map(this)
    private fun Series.mapToSeriesEntity() = dataMapper.seriesToSeriesEntityMapper.map(this)

    private fun Event.mapToEventEntity() = dataMapper.eventToEventEntityMapper.map(this)

}