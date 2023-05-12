package com.example.marvelverse.app.ui.comics


import com.example.marvelverse.domain.entities.main.Comic

sealed interface ComicEvent{
    object BackToHome : ComicEvent
    object ReadyState :ComicEvent

    data class ClickComicEvent(val comic: Comic) : ComicEvent
}