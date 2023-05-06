package com.example.marvelverse.app.ui.events

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvelverse.app.ui.abstracts.BaseFragment
import com.example.marvelverse.databinding.FragmentEventsBinding
import com.example.marvelverse.domain.entities.main.Event


class EventsFragment : BaseFragment<FragmentEventsBinding>(FragmentEventsBinding::inflate) {

    private val eventViewModel: EventsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventsAdapter(eventViewModel)
        binding.eventRecyclerView.adapter = adapter
        binding.viewModel = eventViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        eventViewModel.eventsEvent.observe(viewLifecycleOwner) {
            it?.let {
                handleEvent(it)
            }
        }
    }
    fun handleEvent(event: EventsEvent) {
        when (event) {
            is EventsEvent.ClickEventsEvent ->navigateToDetails(event.event)
            EventsEvent.BackToHome -> BackToHome()
        }
    }
    fun navigateToDetails(event: Event) {
        Log.d("EventFragment", "ClickEventsEvent $event")
    }
    fun BackToHome() {
        Log.d("EventFragment", "BackToHome")
    }
}