package com.example.marvelverse.app.ui.comics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ItemComicBinding
import com.example.marvelverse.domain.entities.main.Comic

class ComicAdapter: ListAdapter<Comic, ComicAdapter.ViewAllComicAdapter>(EventDiffCallback())  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicAdapter.ViewAllComicAdapter {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_comic,parent,false)
        return  ComicAdapter.ViewAllComicAdapter(view)
    }
    override fun onBindViewHolder(
        holder:  ComicAdapter.ViewAllComicAdapter,
        position: Int) {
        val currentList = getItem(position)
        holder.binding.item = currentList


    }
    class ViewAllComicAdapter(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding= ItemComicBinding.bind(itemView)

    }
    class EventDiffCallback : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return areItemsTheSame(oldItem, newItem) && oldItem.title == newItem.title
        }

    }


}

