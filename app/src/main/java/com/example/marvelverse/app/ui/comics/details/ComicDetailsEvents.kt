package com.example.marvelverse.app.ui.comics.details
import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Event

sealed interface ComicDetailsEvents{
data class ClickCharacterEvent(val character: Character) : ComicDetailsEvents
data class ClickEventEvent(val event: Event) : ComicDetailsEvents }