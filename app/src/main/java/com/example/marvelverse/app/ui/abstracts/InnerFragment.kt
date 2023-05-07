package com.example.marvelverse.app.ui.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class InnerFragment<DB : ViewBinding>(inflate: (LayoutInflater, ViewGroup?, Boolean) -> DB) :
    BaseFragment<DB>(inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showAppBar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}