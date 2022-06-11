package com.daniel.weeklyassigment2.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daniel.weeklyassigment2.databinding.TodoItemBinding
import com.daniel.weeklyassigment2.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EventAdapter (
    // clock handling with interface
    private val onEventClickHandler: ClickHandler,
    private val eventsList: MutableList<Event> = Singleton.eventList,
    // click handling with high order function
    private val onClickEventHighOrderFunction: (Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {




    fun updateEventsList(event: Event) {
        eventsList.add(event)
        notifyItemInserted(eventsList.indexOf(event))

        registro(event.name,event.date,event.category)

    }

    fun registro(name:String,date:String,category: String){
        println("registroooooooooo 1")
        FirebaseAuth.getInstance()
            var dbRegister = FirebaseFirestore.getInstance()

            dbRegister.collection("users").document(name).set(hashMapOf(
                "user" to name,
                "dateRegister" to date,
                "category" to category
            ))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            TodoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(eventsList[position], onEventClickHandler, onClickEventHighOrderFunction)

    override fun getItemCount(): Int {
      return eventsList.size
    }
}

class EventViewHolder(
    private val binding: TodoItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event, onEventClickHandler: ClickHandler, onClickEventHighOrderFunction: (Event) -> Unit) {
        binding.eventName.text = event.name
        binding.eventCategory.text = event.category
        binding.eventDate.text = event.date

        itemView.setOnClickListener {
            // this is handling the click with interface
            // onEventClickHandler.onEventItemClick(event)

            // this is handling click with high order function
            onClickEventHighOrderFunction(event)

        }
    }
}