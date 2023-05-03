package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.domain.entities.main.Comic
import com.example.nestedrecyclerview.ui.base.BaseInteractionListener

interface ComicInteractionListener:BaseInteractionListener {
    fun onComicClick(comic: Comic)
}