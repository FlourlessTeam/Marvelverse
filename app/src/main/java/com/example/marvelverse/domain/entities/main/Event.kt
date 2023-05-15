package com.example.marvelverse.domain.entities.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable


@Entity(tableName = "COMIC_TABLE")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Title")   val title: String,
    @ColumnInfo(name = "Description")  val description: String?,
    @ColumnInfo(name = "Series")    val series: RelatedCollectionSeries,
    @ColumnInfo(name = "Comics")   val comics: InfoWrapper,
    @ColumnInfo(name = "Creators")   val creators: InfoWrapper,
    @ColumnInfo(name = "Stories")   val stories: InfoWrapper,
    @ColumnInfo(name = "Characters")   val characters: InfoWrapper,
    @ColumnInfo(name = "Thumbnail")   val thumbnail: Thumbnail,
) : Serializable
