package com.example.marvelverse.app.ui.home

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.domain.entities.Series

sealed interface HomeEvent {
    object ClickSeeAllCharactersEvent : HomeEvent
    object ClickSeeAllComicsEvent : HomeEvent
    object ClickSeeAllEventsEvent : HomeEvent
    object ClickSeeAllSeriesEvent : HomeEvent
    data class ClickCharacterEvent(val character: Character) : HomeEvent
    data class ClickComicEvent(val comic: Comic) : HomeEvent
    data class ClickEventEvent(val event: Event) : HomeEvent
    data class ClickSeriesEvent(val series: Series) : HomeEvent
}