package com.example.marvelverse.app.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelverse.databinding.ItemHomeCardBinding
import com.example.marvelverse.domain.entities.main.Creator

class CreatorAdapter : ListAdapter<Creator, CreatorAdapter.CreatorViewHolder>(CreatorDiffCallBack) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemHomeCardBinding.inflate(layoutInflater, parent, false)
		return CreatorViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
		val creator = getItem(position)
		holder.bind(creator)
	}

	class CreatorViewHolder(val binding: ItemHomeCardBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(creator: Creator) {
			binding.homeCardName.text = creator.fullName

			val thumbnailUrl = creator.thumbnail.path.plus(".").plus(creator.thumbnail.extension)

			Glide.with(binding.root.context).load(thumbnailUrl)
				.into(binding.homeCardImage)
		}
	}

	object CreatorDiffCallBack : DiffUtil.ItemCallback<Creator>() {
		override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean {
			return oldItem == newItem
		}

		override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean {
			return true
		}

	}
}