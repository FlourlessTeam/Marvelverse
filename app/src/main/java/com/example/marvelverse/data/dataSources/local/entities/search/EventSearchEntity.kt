package com.example.marvelverse.data.dataSources.local.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENT_SEARCH_TABLE")
data class EventSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?,
    val series: String?,
    val comics: String?,
    val creators: String?,
    val stories: String?,
    val characters: String?,
    val thumbnail: String?,
)
