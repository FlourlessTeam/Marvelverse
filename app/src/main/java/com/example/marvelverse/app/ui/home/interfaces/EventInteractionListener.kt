package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.domain.entities.main.Event
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

interface EventInteractionListener:BaseInteractionListener {
    fun onEventClick(event: Event)
}