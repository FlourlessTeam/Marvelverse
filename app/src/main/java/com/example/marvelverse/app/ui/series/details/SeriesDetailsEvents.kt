package com.example.marvelverse.app.ui.series.details

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event

sealed interface SeriesDetailsEvents {
    data class ClickCharacterSeries(val character: Character) : SeriesDetailsEvents
    data class ClickComicSeries(val comic: Comic) : SeriesDetailsEvents
    data class ClickEventSeries(val event: Event) : SeriesDetailsEvents

}