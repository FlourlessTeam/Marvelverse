package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marvelverse.data.dataSources.local.entities.CharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {
    @Insert
    fun insertCharacter(character: CharacterEntity): Completable

    @Delete
    fun deleteCharacter(character: CharacterEntity): Completable

    @Update
    fun updateCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Single<List<CharacterEntity>>
}