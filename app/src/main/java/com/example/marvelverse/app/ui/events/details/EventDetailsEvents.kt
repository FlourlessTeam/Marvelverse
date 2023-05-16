package com.example.marvelverse.app.ui.events.details

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Series

sealed interface EventDetailsEvents {
	data class ClickCharacterEvent(val character: Character) : EventDetailsEvents
	data class ClickComicEvent(val comic: Comic) : EventDetailsEvents
	data class ClickSeriesEvent(val series: Series) : EventDetailsEvents
}
