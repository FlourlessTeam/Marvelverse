package com.example.marvelverse.app.ui.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelverse.databinding.ItemSeriesBinding
import com.example.marvelverse.domain.entities.main.Series

class SeriesAdapter(private val onSeriesClickListener: OnSeriesClickListener) :
    ListAdapter<Series, SeriesAdapter.ViewHolder>(SeriesDiffUtil()) {
    class ViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    ItemSeriesBinding
                        .inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                )
            }
        }

        fun bind(series: Series, onSeriesClickListener: OnSeriesClickListener) {
            binding.series = series
            binding.onSeriesClickListener = onSeriesClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onSeriesClickListener)
    }

    interface OnSeriesClickListener {
        fun onClick(series: Series)
    }

    private class SeriesDiffUtil : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }

    }

}