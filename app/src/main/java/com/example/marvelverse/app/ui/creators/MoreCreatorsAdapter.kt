package com.example.marvelverse.app.ui.creators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ItemInViewAllCreatorsBinding
import com.example.marvelverse.domain.entities.main.Creator

class MoreCreatorsAdapter :
    ListAdapter<Creator, MoreCreatorsAdapter.ViewAllCreatorsHolder>(CreatorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllCreatorsHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_in_view_all_creators,parent,false)
        return ViewAllCreatorsHolder(view)
    }


    override fun onBindViewHolder(holder: ViewAllCreatorsHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item=item

    }

    class ViewAllCreatorsHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding= ItemInViewAllCreatorsBinding.bind(itemView)

    }

    class CreatorDiffCallback : DiffUtil.ItemCallback<Creator>() {
        override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean {
            return areItemsTheSame(oldItem, newItem) && oldItem.fullName == newItem.fullName
        }
    }

}