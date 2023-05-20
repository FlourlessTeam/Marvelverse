package com.example.marvelverse.app.ui.comics.details

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Event

sealed interface ComicDetailsEvents {
    data class ClickCharacterEvent(val character: Character) : ComicDetailsEvents
    data class ClickEventEvent(val event: Event) : ComicDetailsEvents
}