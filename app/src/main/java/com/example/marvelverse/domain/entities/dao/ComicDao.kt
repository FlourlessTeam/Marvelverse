package com.example.marvelverse.domain.entities.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marvelverse.domain.entities.main.Comic
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ComicDao {
    @Insert
    fun insertComic(comic: Comic):Completable
    @Delete
    fun deleteComic(comic: Comic):Completable
    @Update
    fun updateComic(comic: Comic):Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComic(): Single<List<Comic>>
}