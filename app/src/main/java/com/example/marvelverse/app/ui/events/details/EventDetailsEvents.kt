package com.example.marvelverse.app.ui.events.details

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series

sealed interface EventDetailsEvents {
	object ReadyState:EventDetailsEvents
	data class ClickCharacterEvent(val character: Character) : EventDetailsEvents
	data class ClickComicEvent(val comic: Comic) : EventDetailsEvents
	data class ClickSeriesEvent(val series: Series) : EventDetailsEvents
}
