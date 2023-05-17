package com.example.marvelverse.data.dataSources.local.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<EventSearchEntity>): Completable

    @Query("SELECT * FROM EVENT_SEARCH_TABLE WHERE title LIKE '%' || :title || '%'")
    fun getAllEvents(title: String): Single<List<EventSearchEntity>>
}