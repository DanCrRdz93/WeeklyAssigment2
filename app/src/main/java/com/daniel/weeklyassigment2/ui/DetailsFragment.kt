package com.daniel.weeklyassigment2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.daniel.weeklyassigment2.R
import com.daniel.weeklyassigment2.databinding.FragmentDetailsBinding
import com.daniel.weeklyassigment2.model.Event

class DetailsFragment : Fragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private var newEvent: Event? = null
    private var newAction: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newEvent = it.getSerializable(AddFragment.EVENT_DATA) as? Event
            newAction = it.getString("ACTION")
        }
        binding.nameTxtView.text = newEvent?.name
        binding.categoryTxtView.text = newEvent?.category
        binding.dateTxtView.text = newEvent?.date
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            newEvent = it.getSerializable(AddFragment.EVENT_DATA) as? Event
        }
        binding.editBtn.setOnClickListener{
            findNavController().navigate(R.id.action_detailsFragment_to_addFragment, bundleOf(Pair(AddFragment.EVENT_DATA,newEvent),Pair("ACTION","PUT")))
        }

        binding.deleteBtn.setOnClickListener{
            findNavController().navigate(R.id.action_detailsFragment_to_mainFragment, bundleOf(Pair(AddFragment.EVENT_DATA,newEvent),Pair("ACTION","DELETE")))
        }

        return binding.root
    }
    companion object {
        const val EVENT_DATA = "EVENT_DATA"
    }
}