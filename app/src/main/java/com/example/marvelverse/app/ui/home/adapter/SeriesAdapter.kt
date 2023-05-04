package com.example.marvelverse.app.ui.home.adapter

import com.example.marvelverse.R
import com.example.marvelverse.domain.entities.main.Series
import com.example.nestedrecyclerview.ui.base.BaseAdapter
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

class SeriesAdapter(private val listener: BaseInteractionListener): BaseAdapter<Series>(listener) {
  override val layoutID: Int= R.layout.item_series_card
}