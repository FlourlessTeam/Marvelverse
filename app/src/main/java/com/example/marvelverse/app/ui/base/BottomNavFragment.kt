package com.example.marvelverse.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BottomNavFragment<DB : ViewBinding>(inflate: (LayoutInflater, ViewGroup?, Boolean) -> DB) :
    BaseFragment<DB>(inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideAppBar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}