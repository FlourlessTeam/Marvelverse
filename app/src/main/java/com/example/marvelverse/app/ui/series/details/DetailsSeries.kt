package com.example.marvelverse.app.ui.series.details

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event

sealed interface DetailsSeries {
    data class ClickCharacterSeries(val character: Character) : DetailsSeries
    data class ClickComicSeries(val comic: Comic) : DetailsSeries
    data class ClickEventSeries(val event: Event) : DetailsSeries

}