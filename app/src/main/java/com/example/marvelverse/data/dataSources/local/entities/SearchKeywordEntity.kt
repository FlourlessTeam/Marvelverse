package com.example.marvelverse.data.dataSources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SEARCH_TABLE")
data class SearchKeywordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val keyword: String
)