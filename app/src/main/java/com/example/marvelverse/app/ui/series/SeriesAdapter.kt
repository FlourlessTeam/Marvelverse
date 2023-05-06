package com.example.marvelverse.app.ui.series

import androidx.recyclerview.widget.DiffUtil
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.abstracts.BaseRecyclerAdapter
import com.example.marvelverse.app.ui.home.interfaces.SeriesInteractionListener
import com.example.marvelverse.databinding.ItemSeriesBinding
import com.example.marvelverse.domain.entities.main.Series
import com.example.nestedrecyclerview.ui.base.BaseAdapter

class SeriesAdapter(private val clickListener: SeriesInteractionListener) :BaseAdapter<Series>(clickListener){
    override val layoutID= R.layout.item_series
}