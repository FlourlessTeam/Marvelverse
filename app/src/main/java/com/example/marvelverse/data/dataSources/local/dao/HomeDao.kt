package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.home.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.home.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.home.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.home.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface HomeDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>): Completable


    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Single<List<CharacterEntity>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicEntity>): Completable


    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Single<List<ComicEntity>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<EventEntity>): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Single<List<EventEntity>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>): Completable


    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Single<List<SeriesEntity>>
}