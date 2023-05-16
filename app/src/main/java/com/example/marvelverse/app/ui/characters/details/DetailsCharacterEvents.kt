package com.example.marvelverse.app.ui.characters.details

import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series

sealed interface DetailsCharacterEvents {
    data class ClickEventEvent(val event : Event) : DetailsCharacterEvents
    data class ClickSeriesEvent(val series: Series) : DetailsCharacterEvents
    data class ClickComicEvent(val comic: Comic) : DetailsCharacterEvents
}