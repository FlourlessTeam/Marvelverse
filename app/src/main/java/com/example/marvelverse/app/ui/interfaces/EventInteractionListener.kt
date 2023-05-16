package com.example.marvelverse.app.ui.interfaces

import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Event


interface EventInteractionListener: BaseInteractionListener {
    fun onEventClick(event: Event)
}