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
import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.example.marvelverse.data.dataSources.remote.reponses.CharacterDto
import com.example.marvelverse.data.dataSources.remote.reponses.ComicDto
import com.example.marvelverse.data.dataSources.remote.reponses.EventDto
import com.example.marvelverse.data.dataSources.remote.reponses.SeriesDto
import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.SearchKeyword
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.domain.mapper.MappersContainer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MarvelRepository @Inject constructor(
    private val marvelApiServices: MarvelApiServices,
    private val dataMapper: MappersContainer,
    private val fakeLocalData: FakeLocalData,
    private val homeDao: HomeDao,
    private val searchDao: SearchDao,
) {
    private val disposable = CompositeDisposable()

    /**
     * Caching search
     */

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
                Single.error(e)
            }
        }
    }

    private fun getCachedCharacters(name: String): List<Character> {
        val savedEntities =
            searchDao.getAllCharacters(name).subscribeOn(Schedulers.io()).blockingGet()
        return savedEntities.map { characterSearchEntity ->
            characterSearchEntity.mapToCharacter()
        }
    }

    fun searchCharacters(limit: Int? = null, title: String? = null): Single<List<Character>> {
        return marvelApiServices.fetchCharacters(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { characterDto ->
                characterDto.mapToCharacter()
            } ?: emptyList()
        }
    }

    private fun cacheCharacters(characters: Single<List<Character>>) {
        val cachedResponse = characters.blockingGet().map { character ->
            character.mapToSearchCharacterEntity()
        }
        searchDao.insertCharacters(cachedResponse).subscribeOn(Schedulers.io()).subscribe()
            .addTo(disposable)
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
                Single.error(e)
            }
        }
    }

    private fun getCachedComics(title: String): List<Comic> {
        val savedEntities = searchDao.getAllComics(title).subscribeOn(Schedulers.io()).blockingGet()
        return savedEntities.map { comicSearchEntity -> comicSearchEntity.mapToComic() }
    }

    fun searchComics(limit: Int? = null, title: String? = null): Single<List<Comic>> {
        return marvelApiServices.fetchComics(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { comicDto ->
                comicDto.mapToComic()
            } ?: emptyList()
        }
    }

    private fun cacheComics(comics: Single<List<Comic>>) {
        val cachedResponse = comics.blockingGet().map { comic -> comic.mapToSearchComicEntity() }
        searchDao.insertComics(cachedResponse).subscribeOn(Schedulers.io()).subscribe()
            .addTo(disposable)
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
                Single.error(e)
            }
        }
    }

    private fun getCachedEvents(title: String): List<Event> {
        val savedEntities = searchDao.getAllEvents(title).subscribeOn(Schedulers.io()).blockingGet()
        return savedEntities.map { eventSearchEntity -> eventSearchEntity.mapToEvent() }
    }


    fun searchEvents(limit: Int? = null, title: String? = null): Single<List<Event>> {
        return marvelApiServices.fetchEvents(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { eventDto ->
                eventDto.mapToEvent()
            } ?: emptyList()
        }
    }

    private fun cacheEvents(events: Single<List<Event>>) {
        val cachedResponse = events.blockingGet().map { event -> event.mapToSearchEventEntity() }
        searchDao.insertEvents(cachedResponse).subscribeOn(Schedulers.io()).subscribe()
            .addTo(disposable)
    }


    fun searchSeries(limit: Int? = null, title: String? = null): Single<List<Series>> {
        return marvelApiServices.fetchSeries(limit, title).map { baseResponse ->
            baseResponse.data?.results?.map { seriesDto ->
                seriesDto.mapToSeries()
            } ?: emptyList()
        }
    }

    fun saveKeyword(keyword: SearchKeyword) {
        disposable.add(searchDao.insertKeyword(keyword.mapToSearchKeywordEntity()).subscribe())
    }

    fun getSearchKeywords(): Single<List<SearchKeyword>> {
        return searchDao.getAllKeywords().map { searchKeywordEntities -> searchKeywordEntities.map { it.mapToSearchKeyword() } }
    }

    /**
     * details
     */

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

    /**
     * Caching home
     */
    fun getHomeItems(): Single<List<HomeItem>> {
        return Single.zip(
            getCharacters().onErrorResumeNext { getCharactersFromDatabase() },
            getComics().onErrorResumeNext { getComicsFromDatabase() },
            getSeries().onErrorResumeNext { getSeriesFromDatabase() },
            getEvents().onErrorResumeNext { getEventsFromDatabase() }
        ) { characters, comics, series, events ->
            listOf(
                HomeItem.CharactersItem(characters),
                HomeItem.ComicsItem(comics),
                HomeItem.EventsItem(events),
                HomeItem.SeriesItem(series)
            )
        }
    }

    private fun getCharacters(): Single<List<Character>> {
        return getRandomCharacters()
            .doOnSuccess { characters ->
                insertCharactersToDatabase(characters)
            }
            .onErrorResumeNext {
                getCharactersFromDatabase()
            }
    }

    private fun getRandomCharacters(): Single<List<Character>> {
        return marvelApiServices.fetchCharacters(RANDOM_CHARACTER_LIMIT, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(RANDOM_CHARACTER_COUNT)
                ?.map { characterDto ->
                    characterDto.mapToCharacter()
                } ?: emptyList()
        }
    }

    private fun insertCharactersToDatabase(characters: List<Character>) {
        homeDao.insertCharacters(characters.map { it.mapToCharacterEntity() })
            .subscribeOn(Schedulers.io()).subscribe().addTo(disposable)
    }

    private fun getCharactersFromDatabase(): Single<List<Character>> {
        return homeDao.getAllCharacters()
            .map { characterEntities -> characterEntities.map { it.mapToChar() } }
            .subscribeOn(Schedulers.io())
            .flatMap { characters ->
                if (characters.isNotEmpty()) {
                    Single.just(characters)
                } else {
                    Single.error(Exception("No characters in database"))

                }
            }
    }


    private fun getComics(): Single<List<Comic>> {
        return getRandomComics()
            .doOnSuccess { comics ->
                insertComicsToDatabase(comics)
            }
            .onErrorResumeNext {
                getComicsFromDatabase()
            }
    }

    private fun getRandomComics(): Single<List<Comic>> {
        return marvelApiServices.fetchComics(RANDOM_COMIC_LIMIT, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(RANDOM_COMIC_COUNT)?.map { comicDto ->
                comicDto.mapToComic()
            } ?: emptyList()
        }
    }

    private fun insertComicsToDatabase(comics: List<Comic>) {
        homeDao.insertComics(comics.map { it.mapToComicEntity() }).subscribeOn(Schedulers.io())
            .subscribe().addTo(disposable)
    }

    private fun getComicsFromDatabase(): Single<List<Comic>> {
        return homeDao.getAllComics()
            .map { comicEntities -> comicEntities.map { it.mapToComic() } }
            .subscribeOn(Schedulers.io())
            .flatMap { comics ->
                if (comics.isNotEmpty()) {
                    Single.just(comics)
                } else {
                    Single.error(Exception("No comics in database"))

                }
            }
    }


    private fun getSeries(): Single<List<Series>> {
        return getRandomSeries()
            .doOnSuccess { series ->
                insertSeriesToDatabase(series)
            }
            .onErrorResumeNext {
                getSeriesFromDatabase()
            }
    }

    private fun getRandomSeries(): Single<List<Series>> {
        return marvelApiServices.fetchSeries(RANDOM_SERIES_LIMIT, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(RANDOM_SERIES_COUNT)?.map { seriesDto ->
                seriesDto.mapToSeries()
            } ?: emptyList()
        }
    }

    private fun insertSeriesToDatabase(series: List<Series>) {
        homeDao.insertSeries(series.map { it.mapToSeriesEntity() }).subscribeOn(Schedulers.io())
            .subscribe().addTo(disposable)
    }

    private fun getSeriesFromDatabase(): Single<List<Series>> {
        return homeDao.getAllSeries()
            .map { it.map { seriesEntity -> seriesEntity.mapToSeries() } }
            .subscribeOn(Schedulers.io())
            .flatMap { series ->
                if (series.isNotEmpty()) {
                    Single.just(series)
                } else {
                    Single.error(Exception("No series in database"))

                }
            }
    }


    private fun getEvents(): Single<List<Event>> {
        return getRandomEvents()
            .doOnSuccess { events ->
                insertEventsToDatabase(events)
            }
            .onErrorResumeNext {
                getEventsFromDatabase()
            }
    }

    private fun getRandomEvents(): Single<List<Event>> {
        return marvelApiServices.fetchEvents(RANDOM_EVENT_LIMIT, null).map { baseResponse ->
            baseResponse.data?.results?.shuffled()?.take(RANDOM_EVENT_COUNT)?.map { eventDto ->
                eventDto.mapToEvent()
            } ?: emptyList()
        }
    }

    private fun insertEventsToDatabase(events: List<Event>) {
        homeDao.insertEvents(events.map { it.mapToEventEntity() }).subscribeOn(Schedulers.io())
            .subscribe().addTo(disposable)
    }

    private fun getEventsFromDatabase(): Single<List<Event>> {
        return homeDao.getAllEvents()
            .map { it.map { eventEntity -> eventEntity.mapToEvent() } }
            .subscribeOn(Schedulers.io())
            .flatMap { events ->
                if (events.isNotEmpty()) {
                    Single.just(events)
                } else {
                    Single.error(Exception("No events in database"))
                }
            }
    }


    /**
     * fake data
     */
    fun getItems() = fakeLocalData.getAboutItems()

    /**
     * mapper extension functions
     */
    private fun ComicDto.mapToComic(): Comic = dataMapper.comicMapper.map(this)
    private fun SeriesDto.mapToSeries(): Series = dataMapper.seriesMapper.map(this)
    private fun CharacterDto.mapToCharacter(): Character = dataMapper.characterMapper.map(this)
    private fun EventDto.mapToEvent(): Event = dataMapper.eventMapper.map(this)

    private fun Character.mapToSearchCharacterEntity(): CharacterSearchEntity =
        dataMapper.charToCharSearchEntityMapper.map(this)

    private fun Comic.mapToSearchComicEntity(): ComicSearchEntity =
        dataMapper.comicToComicSearchEntityMapper.map(this)

    private fun Event.mapToSearchEventEntity(): EventSearchEntity =
        dataMapper.eventToEventSearchEntity.map(this)

    private fun CharacterSearchEntity.mapToCharacter(): Character =
        dataMapper.charSearchEntityToCharMapper.map(this)

    private fun ComicSearchEntity.mapToComic(): Comic =
        dataMapper.comicSearchEntityToComicMapper.map(this)

    private fun EventSearchEntity.mapToEvent(): Event =
        dataMapper.eventSearchEntityToEventMapper.map(this)

    private fun Character.mapToCharacterEntity(): CharacterEntity =
        dataMapper.characterToCharacterEntityMapper.map(this)

    private fun Comic.mapToComicEntity(): ComicEntity =
        dataMapper.comicToComicEntityMapper.map(this)

    private fun CharacterEntity.mapToChar() = dataMapper.characterEntityToCharacterMapper.map(this)
    private fun ComicEntity.mapToComic() = dataMapper.comicEntityToComicMapper.map(this)
    private fun SeriesEntity.mapToSeries() = dataMapper.seriesEntityToSeriesMapper.map(this)
    private fun EventEntity.mapToEvent() = dataMapper.eventEntityToEventMapper.map(this)
    private fun Series.mapToSeriesEntity() = dataMapper.seriesToSeriesEntityMapper.map(this)
    private fun Event.mapToEventEntity() = dataMapper.eventToEventEntityMapper.map(this)

    private fun SearchKeyword.mapToSearchKeywordEntity() =
        dataMapper.keywordToKeywordEntityMapper.map(this)

    private fun SearchKeywordEntity.mapToSearchKeyword() =
        dataMapper.keywordEntityToKeywordMapper.map(this)

    /**
     * Disposable
     */
    private fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }

    fun clearDisposables() {
        disposable.dispose()
    }

    companion object {
        private const val RANDOM_CHARACTER_LIMIT = 80
        private const val RANDOM_COMIC_LIMIT = 50
        private const val RANDOM_SERIES_LIMIT = 50
        private const val RANDOM_EVENT_LIMIT = 50
        private const val RANDOM_CHARACTER_COUNT = 20
        private const val RANDOM_COMIC_COUNT = 10
        private const val RANDOM_SERIES_COUNT = 10
        private const val RANDOM_EVENT_COUNT = 10
    }

}