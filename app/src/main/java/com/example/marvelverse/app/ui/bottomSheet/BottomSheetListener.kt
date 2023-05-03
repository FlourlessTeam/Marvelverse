package com.example.marvelverse.app.ui.bottomSheet

import com.example.marvelverse.app.ui.search.SearchFilter

interface BottomSheetListener {
    fun onButtonClicked(searchFilter: SearchFilter)
}