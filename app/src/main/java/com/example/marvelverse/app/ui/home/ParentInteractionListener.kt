package com.example.marvelverse.app.ui.home

import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Story


interface ParentInteractionListener: BaseInteractionListener {
    fun onViewAllCharactersClick()
    fun onViewAllEventsClick()
    fun onViewAllComicsClick()
    fun onViewAllSeriesClick()
}