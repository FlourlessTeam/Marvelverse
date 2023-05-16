package com.example.marvelverse.data.dataSources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marvelverse.data.dataSources.local.entities.SearchKeywordEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SearchKeywordDao {
    @Insert
    fun insertKeyword(keyword: SearchKeywordEntity): Completable

    @Delete
    fun deleteKeyword(keyword: SearchKeywordEntity): Completable

    @Update
    fun updateKeyword(keyword: SearchKeywordEntity): Completable

    @Query("SELECT * FROM SEARCH_TABLE")
    fun getAllKeywords(): Single<List<SearchKeywordEntity>>
}