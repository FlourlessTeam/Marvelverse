package com.example.marvelverse.app.ui.series
import com.example.marvelverse.domain.entities.main.Series

sealed interface SeriesEvent{
    object BackToHome : SeriesEvent
    data class ClickSeriesEvent(val series: Series) : SeriesEvent
}