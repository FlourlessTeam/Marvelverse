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
    val series: String?,
    val characters: String?,
    val creators: String?,
    val stories: String?,
    val events: String?,
    val thumbnail: String?,
)

