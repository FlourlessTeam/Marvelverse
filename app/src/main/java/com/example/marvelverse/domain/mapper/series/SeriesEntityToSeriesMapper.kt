package com.example.marvelverse.domain.mapper.series

import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
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
            input.comics,
            input.characters,
            input.creators,
            input.stories,
            input.events,
            input.thumbnail
        )
    }
}