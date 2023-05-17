package com.example.marvelverse.data.dataSources.local.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SearchKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeyword(keyword: SearchKeywordEntity): Completable

    @Query("SELECT * FROM KEYWORD_SEARCH_TABLE")
    fun getAllKeywords(): Single<List<SearchKeywordEntity>>
}