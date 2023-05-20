package com.example.marvelverse.data.dataSources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.local.entities.home.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.home.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.home.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.data.dataSources.local.entities.home.SeriesEntity
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity


@Database(
    entities = [ComicEntity::class,
        CharacterEntity::class,
        EventEntity::class,
        SeriesEntity::class,
        SearchKeywordEntity::class,
        CharacterSearchEntity::class,
        ComicSearchEntity::class,
        EventSearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val searchDao: SearchDao
    abstract val homeDao: HomeDao
}
