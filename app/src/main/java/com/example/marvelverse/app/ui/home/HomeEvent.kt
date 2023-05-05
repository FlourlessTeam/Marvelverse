package com.example.marvelverse.app.ui.home

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story

sealed interface HomeEvent {
    object ClickSeeAllCharactersEvent : HomeEvent
    object ClickSeeAllComicsEvent : HomeEvent
    object ClickSeeAllEventsEvent : HomeEvent
    object ClickSeeAllSeriesEvent : HomeEvent
    object ClickSeeAllStoriesEvent : HomeEvent
    data class ClickCharacterEvent(val character: Character) : HomeEvent
    data class ClickComicEvent(val comic: Comic) : HomeEvent
    data class ClickEventEvent(val event: Event) : HomeEvent
    data class ClickSeriesEvent(val series: Series) : HomeEvent
    data class ClickStoryEvent(val story: Story) : HomeEvent
}