package com.example.marvelverse.data.dataSources.local.entities.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_SEARCH_TABLE")
data class CharacterSearchEntity(
    @PrimaryKey val id: Int?,
    val name: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
)