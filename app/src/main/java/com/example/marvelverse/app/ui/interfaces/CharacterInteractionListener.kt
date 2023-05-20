package com.example.marvelverse.app.ui.interfaces

import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Character


interface CharacterInteractionListener: BaseInteractionListener {
    fun onCharacterClick(character: Character)
}