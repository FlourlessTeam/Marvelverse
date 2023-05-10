package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.app.ui.base.BaseAdapter


class EventAdapter(private val listener: BaseInteractionListener): BaseAdapter<Event>(listener) {
  override val layoutID: Int= R.layout.item_home_event_card
}