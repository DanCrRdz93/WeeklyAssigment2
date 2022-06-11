package com.daniel.weeklyassigment2.adapter

import com.daniel.weeklyassigment2.model.Event


interface ClickHandler {
    fun onEventItemClick(event: Event)
}