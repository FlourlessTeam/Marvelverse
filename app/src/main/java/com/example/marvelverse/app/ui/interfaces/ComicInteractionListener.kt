package com.example.marvelverse.app.ui.interfaces

import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Comic


interface ComicInteractionListener: BaseInteractionListener {
    fun onComicClick(comic: Comic)
}