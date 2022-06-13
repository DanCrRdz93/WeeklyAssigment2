package com.daniel.weeklyassigment2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.weeklyassigment2.R
import com.daniel.weeklyassigment2.adapter.ClickHandler
import com.daniel.weeklyassigment2.adapter.EventAdapter
import com.daniel.weeklyassigment2.databinding.FragmentMainBinding
import com.daniel.weeklyassigment2.model.Event

class MainFragment : Fragment(), ClickHandler  {

    private var newEvent: Event? = null
    private var newAction: String? = null

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }




    private val eventAdapter by lazy {
        EventAdapter(this) { event ->
            findNavController().navigate(
                R.id.action_mainFragment_to_addFragment,
                bundleOf(Pair(AddFragment.EVENT_DATA, event))
            )
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            newEvent = it.getSerializable(AddFragment.EVENT_DATA) as? Event
            newAction = it.getString("ACTION")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.listRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
            adapter = eventAdapter
        }

        binding.listRecycler.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
        binding.createEvent.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        newEvent?.let {
            eventAdapter.updateEventsList(it,newAction?: "POST")
            newEvent = null
            arguments = null
        }
    }


    override fun onEventItemClick(event: Event) {


        findNavController().navigate(
            R.id.action_mainFragment_to_addFragment,
            bundleOf(Pair(AddFragment.EVENT_DATA, event))
        )

    }

}