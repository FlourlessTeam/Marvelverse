package com.example.marvelverse.app.ui.characters.details

import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series

sealed interface DetailsCharacterEvents {
    object ReadyState : DetailsCharacterEvents
    data class ClickEventEvent(val event :Event) : DetailsCharacterEvents
    data class ClickSeriesEvent(val series: Series) : DetailsCharacterEvents
    data class ClickComicEvent(val comic: Comic) : DetailsCharacterEvents
}