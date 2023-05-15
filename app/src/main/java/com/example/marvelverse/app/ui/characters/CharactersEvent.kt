package com.example.marvelverse.app.ui.characters


import com.example.marvelverse.domain.entities.main.Character

sealed interface CharactersEvent{
    data class ClickCharacterEvent(val character: Character) : CharactersEvent
}