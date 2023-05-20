package com.example.marvelverse.domain.mapper.series

import com.example.marvelverse.data.dataSources.local.entities.home.SeriesEntity
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class SeriesEntityToSeriesMapper  @Inject constructor(): Mapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            input.id,
            input.title,
            input.description,
            input.resourceURI,
            input.comicsUri,
            input.charactersUri,
            input.creatorsUri,
            input.storiesUri,
            input.eventsUri,
            input.imageUrl
        )
    }
}