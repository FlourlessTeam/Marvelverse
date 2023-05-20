package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.app.ui.base.BaseAdapter


class ComicsAdapter(private val listener: BaseInteractionListener): BaseAdapter<Comic>(listener) {
  override val layoutID: Int= R.layout.item_comic_card
}

