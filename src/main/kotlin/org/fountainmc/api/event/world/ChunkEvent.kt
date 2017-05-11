package org.fountainmc.api.event.world

import org.fountainmc.api.event.Event
import org.fountainmc.api.world.Chunk

interface ChunkEvent : Event {

    val chunk: Chunk

}
