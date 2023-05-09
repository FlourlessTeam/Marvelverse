package com.example.marvelverse.app.ui.interfaces

import com.example.marvelverse.app.ui.base.BaseInteractionListener


interface ParentInteractionListener: BaseInteractionListener {
    fun onViewAllCharactersClick()
    fun onViewAllEventsClick()
    fun onViewAllComicsClick()
    fun onViewAllSeriesClick()
}