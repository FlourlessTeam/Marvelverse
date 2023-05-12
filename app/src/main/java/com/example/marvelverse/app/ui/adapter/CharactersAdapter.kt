package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.domain.entities.main.Character

class CharactersAdapter(private val listener: BaseInteractionListener): BaseAdapter<Character>(listener) {
  override val layoutID: Int= R.layout.item_character_card
}

