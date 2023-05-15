package com.example.marvelverse.domain.entities.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelverse.domain.entities.wrappers.InfoWrapper
import com.example.marvelverse.domain.entities.wrappers.RelatedCollectionSeries
import com.example.marvelverse.domain.entities.wrappers.Thumbnail
import java.io.Serializable

@Entity(tableName = "COMIC_TABLE")
data class Comic(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "Title")  val title: String,
    @ColumnInfo(name = "Description") val description:String?,
    @ColumnInfo(name = "ResourceURI")  val resourceURI: String,
    @ColumnInfo(name = "PageCount") val pageCount:Int,
    @ColumnInfo(name = "Series")  val series: RelatedCollectionSeries,
    @ColumnInfo(name = "Characters")   val characters: InfoWrapper,
    @ColumnInfo(name = "Creators")   val creators: InfoWrapper,
    @ColumnInfo(name = "Stories")   val stories: InfoWrapper,
    @ColumnInfo(name = "Events")   val events: InfoWrapper,
    @ColumnInfo(name = "Thumbnail")   val thumbnail: Thumbnail,
): Serializable

