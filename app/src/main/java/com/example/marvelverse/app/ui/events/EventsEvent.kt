package com.example.marvelverse.app.ui.events



import com.example.marvelverse.domain.entities.Event

sealed interface EventsEvent{
    data class ClickEventsEvent(val event: Event) : EventsEvent
}