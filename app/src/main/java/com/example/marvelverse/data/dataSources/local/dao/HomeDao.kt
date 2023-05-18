package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>)


    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Single<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicEntity>)


    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Single<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<EventEntity>)

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Single<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>)


    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Single<List<SeriesEntity>>
}