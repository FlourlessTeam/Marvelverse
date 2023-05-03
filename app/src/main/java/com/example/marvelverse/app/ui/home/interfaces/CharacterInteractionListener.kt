package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.domain.entities.main.Character
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

interface CharacterInteractionListener:BaseInteractionListener {
    fun onCharacterClick(character: Character)
}