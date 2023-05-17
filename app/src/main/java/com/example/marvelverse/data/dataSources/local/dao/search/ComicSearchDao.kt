package com.example.marvelverse.data.dataSources.local.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ComicSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicSearchEntity>): Completable

    @Query("SELECT * FROM COMIC_SEARCH_TABLE WHERE title LIKE '%' || :title || '%'")
    fun getAllComics(title: String): Single<List<ComicSearchEntity>>
}