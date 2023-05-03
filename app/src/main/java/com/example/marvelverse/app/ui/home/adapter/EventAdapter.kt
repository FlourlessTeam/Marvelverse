package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Event
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class EventAdapter(val eventList: List<Event>, private val listener: OnEventClickListener): BaseAdapter<Event>(eventList,listener) {
  override val layoutID: Int= R.layout.item_event_card
}
interface OnEventClickListener: BaseInteractionListener {
  fun onEventClick(event:Event)
}
interface OnViewAllEventsClickListener: BaseInteractionListener {
  fun onViewAllEventsClick()
}