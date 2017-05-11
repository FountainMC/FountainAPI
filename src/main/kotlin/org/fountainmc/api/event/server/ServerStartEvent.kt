package org.fountainmc.api.event.server

import org.fountainmc.api.Server
import org.fountainmc.api.event.Event

interface ServerStartEvent : Event {

    val server: Server

    companion object {
        fun create(server: Server): ServerStartEvent {
            class SimpleServerStartEvent(override val server: Server): ServerStartEvent
            return SimpleServerStartEvent(server)
        }
    }
}
