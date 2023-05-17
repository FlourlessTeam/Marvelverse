package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SeriesDao {
    @Insert
    fun insertSeries(series: List<SeriesEntity>): Completable


    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Single<List<SeriesEntity>>
}