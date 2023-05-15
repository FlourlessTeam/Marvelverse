package com.example.marvelverse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable

@Entity("EVENT_TABLE")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String?,
    val series: RelatedCollectionSeries,
    val comics: InfoWrapper,
    val creators: InfoWrapper,
    val stories: InfoWrapper,
    val characters: InfoWrapper,
    val thumbnail: Thumbnail,
) : Serializable
