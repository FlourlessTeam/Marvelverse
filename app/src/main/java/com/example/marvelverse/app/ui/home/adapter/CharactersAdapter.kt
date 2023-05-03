package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Character

class CharactersAdapter(val characterList: List<Character>,private val listener: BaseInteractionListener): BaseAdapter<Character>(characterList,listener) {
  override val layoutID: Int= R.layout.item_character_card
}
interface OnCharacterClickListener: BaseInteractionListener {
  fun onCharacterClick(character:Character)
}
interface OnViewAllCharactersClickListener: BaseInteractionListener {
  fun onViewAllCharactersClick()
}