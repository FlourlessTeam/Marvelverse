package com.example.marvelverse.app.ui.characters

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.databinding.ItemCharacterBinding
import com.example.marvelverse.domain.entities.main.Character

class CharactersAdapter(private val onCharacterClickListener: OnCharacterClickListener) :
    BaseRecyclerAdapter<Character, ItemCharacterBinding>(
        CharactersDiffUtil(),
        ItemCharacterBinding::inflate,
        {}) {


    interface OnCharacterClickListener {
        fun onClick(character: Character)
    }

    private class CharactersDiffUtil : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    override fun BaseViewHolder<Character, ItemCharacterBinding>.bind(
        marvelData: Character,
        onViewClicked: (Character) -> Unit
    ) {
        binding.character = marvelData
        binding.onCharacterClickListener = onCharacterClickListener
    }

}