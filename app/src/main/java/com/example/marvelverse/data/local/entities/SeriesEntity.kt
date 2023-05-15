package com.example.marvelverse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable

@Entity("SERIES_TABLE")
data class SeriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val comics: InfoWrapper,
    val characters: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
) : Serializable
