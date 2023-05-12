package com.example.marvelverse.app.ui.series

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.interfaces.SeriesInteractionListener
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.app.ui.base.BaseAdapter

class SeriesAdapter(private val clickListener: SeriesInteractionListener) : BaseAdapter<Series>(clickListener){
    override val layoutID= R.layout.item_series
}