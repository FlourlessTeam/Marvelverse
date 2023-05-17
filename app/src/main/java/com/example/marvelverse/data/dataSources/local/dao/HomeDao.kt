package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface HomeDao {
    @Insert
    fun insertCharacters(characters: List<CharacterEntity>): Completable


    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Single<List<CharacterEntity>>

    @Insert
    fun insertComics(comics: List<ComicEntity>): Completable


    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Single<List<ComicEntity>>

    @Insert
    fun insertEvents(events: List<EventEntity>): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Single<List<EventEntity>>

    @Insert
    fun insertSeries(series: List<SeriesEntity>): Completable


    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Single<List<SeriesEntity>>
}