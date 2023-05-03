package com.example.marvelverse.app.ui.home

import com.example.marvelverse.domain.entities.main.Character
import com.example.marvelverse.domain.entities.main.Comic
import com.example.marvelverse.domain.entities.main.Event
import com.example.marvelverse.domain.entities.main.Series
import com.example.marvelverse.domain.entities.main.Story

sealed class HomeItem(val rank:Int) {
    class EventsItem(val eventList: List<Event>): HomeItem(0)
    class ComicsItem(val comicsList: List<Comic>): HomeItem(1)
    class SeriesItem(val seriesList: List<Series>): HomeItem(2)
    class StoriesItem(val storiesList: List<Story>): HomeItem(3)
    class CharactersItem(val charactersList: List<Character>): HomeItem(4)
}