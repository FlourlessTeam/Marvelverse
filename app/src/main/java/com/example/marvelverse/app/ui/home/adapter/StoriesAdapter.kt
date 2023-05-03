package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Story
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class StoriesAdapter (val storiesList: List<Story>, private val listener: OnStoriesClickListener): BaseAdapter<Story>(storiesList,listener) {
  override val layoutID: Int= R.layout.item_stories_card
}
interface OnStoriesClickListener: BaseInteractionListener {
  fun onStoriesClick(stories:Story)
}
interface OnViewAllStoriesClickListener: BaseInteractionListener {
  fun onViewAllStoriesClick()
}