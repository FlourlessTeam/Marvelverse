package com.example.marvelverse.app.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.databinding.ItemHomeCardBinding
import com.example.marvelverse.domain.entities.main.Comic

class ComicsAdapter :
    ListAdapter<Comic, ComicsAdapter.ComicViewHolder>(ComicsDiffCallBack) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeCardBinding.inflate(inflater, parent, false)
        return ComicViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = getItem(position)
        holder.bind(comic)
    }

    class ComicViewHolder(private val binding:ItemHomeCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            binding.comic = comic
            binding.executePendingBindings()
        }

    }

object ComicsDiffCallBack :DiffUtil.ItemCallback<Comic>(){
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return true
    }

}


}