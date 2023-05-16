package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Event
import com.example.marvelverse.app.ui.base.BaseAdapter

class EventDetailsAdapter(private val listener: BaseInteractionListener): BaseAdapter<Event>(listener) {
    override val layoutID: Int= R.layout.item_event_card
}