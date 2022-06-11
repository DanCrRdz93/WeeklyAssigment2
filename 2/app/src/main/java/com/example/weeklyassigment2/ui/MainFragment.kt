package com.example.weeklyassigment2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weeklyassigment2.R
import com.example.weeklyassigment2.adapter.ClickHandler
import com.example.weeklyassigment2.adapter.EventAdapter
import com.example.weeklyassigment2.databinding.FragmentMainBinding
import com.example.weeklyassigment2.model.Event

class MainFragment : Fragment(), ClickHandler {

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val eventAdapter by lazy {
        EventAdapter(this) { event ->
            findNavController().navigate(
                R.id.action_mainFragment_to_entryFragment,
                bundleOf(Pair(EntryFragment.EVENT_DATA, event))
            )
        }
    }

    private var newEvent: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            newEvent = it.getSerializable(EntryFragment.EVENT_DATA) as? Event
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.todoRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
            adapter = eventAdapter
        }

        binding.createEvent.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_entryFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        newEvent?.let {
            eventAdapter.updateEventsList(it)
            newEvent = null
            arguments = null
        }
    }

    override fun onEventItemClick(event: Event) {
        findNavController().navigate(
            R.id.action_mainFragment_to_entryFragment,
            bundleOf(Pair(EntryFragment.EVENT_DATA, event))
        )
    }
}