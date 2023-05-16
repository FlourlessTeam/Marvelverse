package com.example.marvelverse.data.dataSources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String?,
    val description: String?,
    val series: String?,
    val comics: String?,
    val stories: String?,
    val events: String?,
    val thumbnail: String?,
)