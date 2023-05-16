package com.example.marvelverse.data.local

import android.content.Context
import com.example.marvelverse.data.local.entities.CharacterEntity
import com.example.marvelverse.data.local.entities.ComicEntity
import com.example.marvelverse.data.local.entities.EventEntity
import com.example.marvelverse.data.local.entities.SearchKeywordEntity
import com.example.marvelverse.data.local.entities.SeriesEntity

class DatabaseRepository(context: Context) {
    private val characterDao = MarvelDatabase.getInstance(context).characterDao
    private val comicDao = MarvelDatabase.getInstance(context).comicDao
    private val eventDao = MarvelDatabase.getInstance(context).eventDao
    private val seriesDao = MarvelDatabase.getInstance(context).seriesDao
    private val searchKeywordDao = MarvelDatabase.getInstance(context).keywordDao

    fun insertCharacter(character: CharacterEntity) {
        characterDao.insertCharacter(character)
    }

    fun insertComic(comic: ComicEntity) {
        comicDao.insertComic(comic)
    }

    fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }

    fun insertSeries(series: SeriesEntity) {
        seriesDao.insertSeries(series)
    }

    fun insertKeyword(keyword: SearchKeywordEntity) {
        searchKeywordDao.insertKeyword(keyword)
    }

    fun deleteCharacter(character: CharacterEntity) {
        characterDao.deleteCharacter(character)
    }

    fun deleteComic(comic: ComicEntity) {
        comicDao.deleteComic(comic)
    }

    fun deleteEvent(event: EventEntity) {
        eventDao.deleteEvent(event)
    }

    fun deleteSeries(series: SeriesEntity) {
        seriesDao.deleteSeries(series)
    }

    fun deleteKeyword(keyword: SearchKeywordEntity) {
        searchKeywordDao.deleteKeyword(keyword)
    }

    fun getAllCharacters() = characterDao.getAllCharacters()
    fun getAllComics() = comicDao.getAllComics()
    fun getAllEvents() = eventDao.getAllEvents()
    fun getAllSeries() = seriesDao.getAllSeries()
    fun getAllKeywords() = searchKeywordDao.getAllKeywords()


}