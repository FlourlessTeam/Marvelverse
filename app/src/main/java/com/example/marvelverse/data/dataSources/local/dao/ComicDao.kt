package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.ComicEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ComicDao {
    @Insert
    fun insertComics(comics: List<ComicEntity>): Completable


    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Single<List<ComicEntity>>
}