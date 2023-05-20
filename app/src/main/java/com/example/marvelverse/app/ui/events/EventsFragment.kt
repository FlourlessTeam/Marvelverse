package com.example.marvelverse.app.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelverse.app.ui.base.InnerFragment
import com.example.marvelverse.databinding.FragmentEventsBinding
import com.example.marvelverse.domain.entities.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : InnerFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate) {

    private val viewModel: EventsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventsAdapter(viewModel)
        binding.eventRecyclerView.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.eventsEvent.observe(viewLifecycleOwner) { event->
            event.getUnHandledData()?.let {
                handleEvent(it)
            }
        }
    }

    fun handleEvent(event: EventsEvent) {
        when (event) {
            is EventsEvent.ClickEventsEvent -> navigateToDetails(event.event)
        }
    }

    fun navigateToDetails(event: Event) {
        val direction = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(event)
        findNavController().navigate(direction)
    }
}