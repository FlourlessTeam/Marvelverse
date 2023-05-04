package com.example.marvelverse.app.ui.home

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

interface ParentInteractionListener: BaseInteractionListener {
    fun onStoriesClick(stories: Story)
    fun onViewAllCharactersClick()
    fun onViewAllEventsClick()
    fun onViewAllComicsClick()
    fun onViewAllStoriesClick()
    fun onViewAllSeriesClick()
}