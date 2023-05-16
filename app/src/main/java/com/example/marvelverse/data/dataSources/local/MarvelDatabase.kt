package com.example.marvelverse.data.dataSources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelverse.data.dataSources.local.dao.CharacterDao
import com.example.marvelverse.data.dataSources.local.dao.ComicDao
import com.example.marvelverse.data.dataSources.local.dao.EventDao
import com.example.marvelverse.data.dataSources.local.dao.SearchKeywordDao
import com.example.marvelverse.data.dataSources.local.dao.SeriesDao
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.SearchKeywordEntity
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity


@Database(
    entities = [ComicEntity::class,
        CharacterEntity::class,
        EventEntity::class,
        SeriesEntity::class,
        SearchKeywordEntity::class],
    version = 1
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val comicDao: ComicDao
    abstract val characterDao: CharacterDao
    abstract val seriesDao: SeriesDao
    abstract val eventDao: EventDao
    abstract val keywordDao: SearchKeywordDao

    companion object {
        @Volatile
        private var instance: MarvelDatabase? = null
        fun getInstance(context: Context): MarvelDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }

        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MarvelDatabase::class.java, "app_database").build()


    }

}
