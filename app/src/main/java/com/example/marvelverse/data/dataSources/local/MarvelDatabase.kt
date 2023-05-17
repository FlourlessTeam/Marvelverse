package com.example.marvelverse.data.dataSources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
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

    companion object {
        @Volatile
        private var instance: MarvelDatabase? = null
        fun getInstance(context: Context): MarvelDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(context).also { instance = it }
            }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MarvelDatabase::class.java, "app_database").build()


    }

}
