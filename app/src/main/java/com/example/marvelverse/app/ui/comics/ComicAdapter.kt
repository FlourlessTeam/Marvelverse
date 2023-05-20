package com.example.marvelverse.app.ui.comics
import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseInteractionListener
import com.example.marvelverse.domain.entities.Comic
import com.example.marvelverse.app.ui.base.BaseAdapter


    class ComicAdapter(listener: BaseInteractionListener) :
        BaseAdapter<Comic>(listener) {
        override val layoutID: Int = R.layout.item_comic
    }



