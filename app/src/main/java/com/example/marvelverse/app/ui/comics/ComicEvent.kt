package com.example.marvelverse.app.ui.comics


import com.example.marvelverse.domain.entities.Comic

sealed interface ComicEvent{

    data class ClickComicEvent(val comic: Comic) : ComicEvent
}