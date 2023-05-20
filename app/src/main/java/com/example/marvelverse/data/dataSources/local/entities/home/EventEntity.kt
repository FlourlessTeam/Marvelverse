package com.example.marvelverse.data.dataSources.local.entities.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EVENT_TABLE")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?,
    val description: String?,
    val seriesUri: String?,
    val comicsUri: String?,
    val creatorsUri: String?,
    val storiesUri: String?,
    val charactersUri: String?,
    val imageUrl: String?,
)
