package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Event


interface EventInteractionListener: BaseInteractionListener {
    fun onEventClick(event: Event)
}