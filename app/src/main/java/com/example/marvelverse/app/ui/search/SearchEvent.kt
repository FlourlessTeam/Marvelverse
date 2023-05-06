package com.example.marvelverse.app.ui.search

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event

sealed interface SearchEvent {

    object ReadyState:SearchEvent

    data class ClickCharacterEvent(val character: Character) : SearchEvent
    data class ClickComicEvent(val comic: Comic) : SearchEvent
    data class ClickEventEvent(val event: Event) : SearchEvent
}