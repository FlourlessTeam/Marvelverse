package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Comic
import com.example.nestedrecyclerview.ui.base.BaseAdapter


class ComicsAdapter(private val listener: BaseInteractionListener): BaseAdapter<Comic>(listener) {
  override val layoutID: Int= R.layout.item_comic_card
}

