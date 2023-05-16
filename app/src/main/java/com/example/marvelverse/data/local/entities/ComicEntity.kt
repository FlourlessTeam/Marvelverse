package com.example.marvelverse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val pageCount: Int,
    val series: String?,
    val characters: String?,
    val creators: String?,
    val stories: String?,
    val events: String?,
    val thumbnail: String?,
) : Serializable

