package com.example.marvelverse.app.ui.home.interfaces

import com.example.marvelverse.app.ui.home.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.main.Comic


interface ComicInteractionListener: BaseInteractionListener {
    fun onComicClick(comic: Comic)
}