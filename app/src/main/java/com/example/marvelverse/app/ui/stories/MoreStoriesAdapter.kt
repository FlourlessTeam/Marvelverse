package com.example.marvelverse.app.ui.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ItemInViewAllStoriesBinding
import com.example.marvelverse.domain.entities.main.Story

class MoreStoriesAdapter(private val listener: MoreStoriesListener):
    ListAdapter<Story, MoreStoriesAdapter.ViewAllStoriesHolder>(StoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllStoriesHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_in_view_all_stories,parent,false)
        return ViewAllStoriesHolder(view)
    }

    override fun onBindViewHolder(holder: ViewAllStoriesHolder, position: Int) {
        val item = getItem(position)
        holder.binding.item=item
        holder.binding.listener=listener

    }

    class ViewAllStoriesHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding=ItemInViewAllStoriesBinding.bind(itemView)

    }

    class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return areItemsTheSame(oldItem, newItem) && oldItem.title == newItem.title
        }
    }

}

interface MoreStoriesListener{
    fun onClick(story: Story)
}