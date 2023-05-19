package com.example.marvelverse.data.dataSources.local.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("COMIC_SEARCH_TABLE")
data class ComicSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val pageCount: Int?,
    val seriesUri: String?,
    val charactersUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
)

