package com.example.marvelverse.app.ui.search.utils

import com.example.marvelverse.domain.entities.Character
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.utilites.DataState

data class SearchItems(
    val comics: DataState<Comic>,
    val characters: DataState<Character>,
    val events: DataState<Event>
)

