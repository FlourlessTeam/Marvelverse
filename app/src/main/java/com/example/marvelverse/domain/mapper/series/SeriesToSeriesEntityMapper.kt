package com.example.marvelverse.domain.mapper.series

import com.example.marvelverse.data.dataSources.local.entities.SeriesEntity
import com.example.marvelverse.domain.entities.Series
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class SeriesToSeriesEntityMapper  @Inject constructor() : Mapper<Series, SeriesEntity> {
    override fun map(input: Series): SeriesEntity {
        return SeriesEntity(
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