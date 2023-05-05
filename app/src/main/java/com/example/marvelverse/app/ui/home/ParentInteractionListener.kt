package com.example.marvelverse.app.ui.home

import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Story


interface ParentInteractionListener: BaseInteractionListener {
    fun onStoriesClick(stories: Story)
    fun onViewAllCharactersClick()
    fun onViewAllEventsClick()
    fun onViewAllComicsClick()
    fun onViewAllStoriesClick()
    fun onViewAllSeriesClick()
}