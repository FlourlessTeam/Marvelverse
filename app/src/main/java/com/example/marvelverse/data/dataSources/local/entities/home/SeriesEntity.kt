package com.example.marvelverse.data.dataSources.local.entities.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SERIES_TABLE")
data class SeriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val comicsUri: String?,
    val charactersUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
)
