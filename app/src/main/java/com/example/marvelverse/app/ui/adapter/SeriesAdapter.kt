package com.example.marvelverse.app.ui.adapter

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.app.ui.base.BaseAdapter


class SeriesAdapter(private val listener: BaseInteractionListener): BaseAdapter<Series>(listener) {
  override val layoutID: Int= R.layout.item_series_card
}