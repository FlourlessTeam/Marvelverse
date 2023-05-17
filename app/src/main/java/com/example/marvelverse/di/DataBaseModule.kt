package com.example.marvelverse.di

import android.content.Context
import androidx.room.Room
import com.example.marvelverse.data.dataSources.local.MarvelDatabase
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
    ): MarvelDatabase =
        Room.databaseBuilder(context, MarvelDatabase::class.java, "MovieDatabase")
            .build()

    @Singleton
    @Provides
    fun provideSearchDao(database: MarvelDatabase): SearchDao {
        return database.searchDao
    }

    @Singleton
    @Provides
    fun provideHomeDao(database: MarvelDatabase): HomeDao {
        return database.homeDao
    }

}