package com.example.marvelverse.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail

@Entity("CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String?,
    val series: RelatedCollectionSeries,
    val comics: InfoWrapper,
    val stories: InfoWrapper,
    val events: InfoWrapper,
    val thumbnail: Thumbnail,
)