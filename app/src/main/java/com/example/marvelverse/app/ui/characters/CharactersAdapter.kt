package com.example.marvelverse.app.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.databinding.ItemCharacterBinding
import com.example.marvelverse.domain.entities.main.Character

class CharactersAdapter(private val onCharacterClickListener: OnCharacterClickListener) :
    ListAdapter<Character, CharactersAdapter.ViewHolder>(CharactersDiffUtil()) {
    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    ItemCharacterBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }
        }

        fun bind(character: Character, onCharacterClickListener: OnCharacterClickListener) {
            binding.character = character
            binding.onCharacterClickListener = onCharacterClickListener
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onCharacterClickListener)
    }
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
}