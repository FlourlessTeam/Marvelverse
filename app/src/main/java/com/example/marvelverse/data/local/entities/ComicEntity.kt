package com.example.marvelverse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable

@Entity("COMIC_TABLE")
data class ComicEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val pageCount: Int,
    val series: RelatedCollectionSeries,
    val characters: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
) : Serializable

