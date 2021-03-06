package com.learn.appsamples.oneTimeEvents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OneTimeViewModel: ViewModel() {

    sealed class Event {
        class ErrorEvent(val message:String): Event()
        class ToastMessage(val message:String): Event()
    }

    private var events = Channel<Event>()
    val eventsFlow = events.receiveAsFlow()

    fun triggerEvent(){
        val toastMessage = "Using Channels to generate one time event"
        viewModelScope.launch {
            events.send(Event.ToastMessage(toastMessage))
        }
    }
}