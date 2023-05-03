package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Comic
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class ComicsAdapter(val comicList: List<Comic>,private val listener: BaseInteractionListener): BaseAdapter<Comic>(comicList,listener) {
  override val layoutID: Int= R.layout.item_comic_card
}
