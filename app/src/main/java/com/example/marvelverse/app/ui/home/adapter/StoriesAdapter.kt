package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class StoriesAdapter (private val listener: BaseInteractionListener): BaseAdapter<Story>(listener) {
  override val layoutID: Int= R.layout.item_stories_card
}
