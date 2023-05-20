package com.example.marvelverse.app.ui.interfaces

import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Series

interface SeriesInteractionListener: BaseInteractionListener {
    fun onSeriesClick(series: Series)
}