package com.example.marvelverse.data.dataSources.local.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterSearchEntity>): Completable

    @Query("SELECT * FROM CHARACTER_SEARCH_TABLE WHERE name LIKE '%' || :name || '%'")
    fun getAllCharacters(name: String): Single<List<CharacterSearchEntity>>
}