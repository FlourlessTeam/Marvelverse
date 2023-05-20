package com.example.marvelverse.data.dataSources.local.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENT_SEARCH_TABLE")
data class EventSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val charactersUri: String?,
    val imageUrl: String?,
)
