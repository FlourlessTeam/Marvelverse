package com.example.marvelverse.app.ui.history

import com.example.marvelverse.R
import com.example.marvelverse.app.ui.base.BaseAdapter
import com.example.marvelverse.domain.entities.main.About

class AboutAdapter() : BaseAdapter<About>(null) {
    override val layoutID: Int= R.layout.item_card_about
}