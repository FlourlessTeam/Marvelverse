package com.example.marvelverse.domain.entities.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable

@Entity(tableName = "CHARACTER_TABLE")
data class Character(
    @PrimaryKey(autoGenerate = true)  val id: Int,
    @ColumnInfo(name = "Name")  val name: String,
    @ColumnInfo(name = "Description")  val description: String?,
    @ColumnInfo(name = "Series") val series: RelatedCollectionSeries,
    @ColumnInfo(name = "Comics")  val comics: InfoWrapper,
    @ColumnInfo(name = "Stories")   val stories: InfoWrapper,
    @ColumnInfo(name = "Events")   val events: InfoWrapper,
    @ColumnInfo(name = "Thumbnail")   val thumbnail: Thumbnail,
):Serializable
