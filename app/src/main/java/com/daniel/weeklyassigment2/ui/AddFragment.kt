package com.daniel.weeklyassigment2.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daniel.weeklyassigment2.R
import com.daniel.weeklyassigment2.databinding.FragmentAddBinding
import com.daniel.weeklyassigment2.model.Event
import java.text.SimpleDateFormat

class AddFragment : Fragment() {

    private val binding by lazy {
        FragmentAddBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val SimpleF = SimpleDateFormat("MM-dd-yyyy")
        var date: String
        binding.calendarEvent.setOnDateChangeListener { view, year, month, dayOfMonth ->
            date = "${month+1}/$dayOfMonth/$year"
            Toast.makeText(this.context, date, Toast.LENGTH_LONG).show()
        }.let {  date = SimpleF.format(binding.calendarEvent.date)
        }

        // Inflate the layout for this fragment

        binding.eventNameEntry.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // no-op
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.saveEventBtn.isEnabled = p0?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {
                // no-op
            }
        })



        binding.saveEventBtn.setOnClickListener {
            val name = binding.eventNameEntry.text.toString()
            val category = binding.eventCategoryEntry.text.toString()

            Event(name, category, date).also {
                findNavController().navigate(
                    R.id.action_addFragment_to_mainFragment, bundleOf(
                        Pair(EVENT_DATA, it)
                    ))
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {
        const val EVENT_DATA = "EVENT_DATA"
    }
}