package com.example.marvelverse.app.ui.series.details

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event

sealed interface DetailsSeries {
    data class ClickCharacterSeries(val character: Character) : DetailsSeries
    data class ClickComicSeries(val comic: Comic) : DetailsSeries
    data class ClickEventSeries(val event: Event) : DetailsSeries

}