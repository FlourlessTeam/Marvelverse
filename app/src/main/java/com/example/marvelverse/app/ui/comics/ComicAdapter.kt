package com.example.marvelverse.app.ui.comics
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.home.interfaces.ComicInteractionListener
import com.example.marvelverse.domain.entities.main.Comic
import com.example.nestedrecyclerview.ui.base.BaseAdapter


    class ComicAdapter(listener: ComicInteractionListener) :
        BaseAdapter<Comic>(listener) {
        override val layoutID: Int = R.layout.item_comic
    }



