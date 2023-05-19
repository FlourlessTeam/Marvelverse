package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.search.CharacterSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.ComicSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.EventSearchEntity
import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterSearchEntity>): Completable

    @Query("SELECT * FROM CHARACTER_SEARCH_TABLE WHERE name LIKE '%' || :name || '%'")
    fun getAllCharacters(name: String): Single<List<CharacterSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicSearchEntity>): Completable

    @Query("SELECT * FROM COMIC_SEARCH_TABLE WHERE title LIKE '%' || :title || '%'")
    fun getAllComics(title: String): Single<List<ComicSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<EventSearchEntity>): Completable

    @Query("SELECT * FROM EVENT_SEARCH_TABLE WHERE title LIKE '%' || :title || '%'")
    fun getAllEvents(title: String): Single<List<EventSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeyword(keyword: SearchKeywordEntity): Completable

    @Query("SELECT * FROM KEYWORD_SEARCH_TABLE ORDER BY timestamp DESC")
    fun getAllKeywords(): Single<List<SearchKeywordEntity>>

}