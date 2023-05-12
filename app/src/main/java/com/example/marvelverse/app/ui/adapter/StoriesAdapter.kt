package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Story
import com.example.marvelverse.app.ui.base.BaseAdapter


class StoriesAdapter (private val listener: BaseInteractionListener): BaseAdapter<Story>(listener) {
  override val layoutID: Int= R.layout.item_stories_card
}
