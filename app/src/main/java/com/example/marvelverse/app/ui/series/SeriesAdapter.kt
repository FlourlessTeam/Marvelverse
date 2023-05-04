package com.example.marvelverse.app.ui.series

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.databinding.ItemSeriesBinding
import com.example.marvelverse.domain.entities.main.Series

class SeriesAdapter(private val clickListener: ClickListener) : BaseRecyclerAdapter<Series, ItemSeriesBinding>(
        SeriesDiffUtil(),
        ItemSeriesBinding::inflate,
        {}
    ) {
    private class SeriesDiffUtil : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }
    }
    interface ClickListener {
        fun onClick(series: Series)
    }
    override fun BaseViewHolder<Series, ItemSeriesBinding>.bind(
        marvelData: Series,
        onViewClicked: (Series) -> Unit
    ) {
        binding.series = marvelData
        binding.clickListener=clickListener
    }
}
