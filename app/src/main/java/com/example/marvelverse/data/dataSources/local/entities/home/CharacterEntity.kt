package com.example.marvelverse.data.dataSources.local.entities.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val storiesUri: String?,
    val eventsUri: String?,
    val imageUrl: String?,
)