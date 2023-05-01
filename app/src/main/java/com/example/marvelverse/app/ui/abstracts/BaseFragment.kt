package com.example.marvelverse.app.ui.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<DB : ViewBinding>
    (val inflate: (LayoutInflater, ViewGroup?, Boolean) -> DB) : Fragment() {
    protected lateinit var binding: DB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(layoutInflater, container, false)
        return binding.root
    }
}