package com.example.marvelverse.app.ui.events.details

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Series

sealed interface DetailsEvent {
	data class ClickCharacterEvent(val character: Character) : DetailsEvent
	data class ClickComicEvent(val comic: Comic) : DetailsEvent
	data class ClickSeriesEvent(val series: Series) : DetailsEvent
}
