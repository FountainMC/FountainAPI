package org.fountainmc.api.event.server

import org.fountainmc.api.Server
import org.fountainmc.api.event.Event

interface ServerStopEvent : Event {

    val server: Server

    companion object {
        fun create(server: Server): ServerStopEvent {
            class SimpleServerStopEvent(override val server: Server): ServerStopEvent
            return SimpleServerStopEvent(server)
        }
    }
}
