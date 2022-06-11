package com.example.weeklyassigment2.adapter

import com.example.weeklyassigment2.model.Event


interface ClickHandler {
    fun onEventItemClick(event: Event)
}