package com.example.marvelverse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marvelverse.data.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SeriesDao {
    @Insert
    fun insertSeries(seriesEntity: SeriesEntity): Completable

    @Delete
    fun deleteSeries(seriesEntity: SeriesEntity): Completable

    @Update
    fun updateSeries(seriesEntity: SeriesEntity): Completable

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Single<List<SeriesEntity>>
}