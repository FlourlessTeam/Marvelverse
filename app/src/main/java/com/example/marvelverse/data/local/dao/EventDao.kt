package com.example.marvelverse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marvelverse.data.local.entities.EventEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: EventEntity): Completable

    @Delete
    fun deleteEvent(event: EventEntity): Completable

    @Update
    fun updateEvent(event: EventEntity): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Single<List<EventEntity>>
}