package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Series

interface SeriesInteractionListener: BaseInteractionListener {
    fun onSeriesClick(series: Series)
}