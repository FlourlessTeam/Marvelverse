package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Event
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class EventAdapter(private val listener: BaseInteractionListener): BaseAdapter<Event>(listener) {
  override val layoutID: Int= R.layout.item_home_event_card
}