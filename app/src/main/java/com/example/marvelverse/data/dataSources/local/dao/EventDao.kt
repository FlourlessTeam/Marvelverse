package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.EventEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventDao {
    @Insert
    fun insertEvents(events: List<EventEntity>): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Single<List<EventEntity>>
}