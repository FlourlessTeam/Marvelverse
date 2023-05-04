package com.example.marvelverse.app.ui.events
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.R
import com.example.marvelverse.databinding.ItemEventBinding
import com.example.marvelverse.domain.entities.main.Event

class EventsAdapter: ListAdapter<Event, EventsAdapter.ViewAllEventHolder>(EventDiffCallback())  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.ViewAllEventHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_event,parent,false)
        return EventsAdapter.ViewAllEventHolder(view)
    }
    override fun onBindViewHolder(
        holder: EventsAdapter.ViewAllEventHolder,
        position: Int) {
        val currentList = getItem(position)
        holder.binding.item = currentList
    }
    class ViewAllEventHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val binding= ItemEventBinding.bind(itemView)

    }
    class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return areItemsTheSame(oldItem, newItem) && oldItem.title == newItem.title
        }

    }


}

