package com.example.marvelverse.app.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentEventsBinding


class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate) {

	private val viewModel: EventViewModel by viewModels()
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.viewModel = viewModel
		binding.lifecycleOwner = this
	}

}