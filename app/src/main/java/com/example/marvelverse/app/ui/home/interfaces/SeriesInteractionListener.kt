package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.domain.entities.main.Series
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

interface SeriesInteractionListener:BaseInteractionListener {
    fun onSeriesClick(series: Series)
}