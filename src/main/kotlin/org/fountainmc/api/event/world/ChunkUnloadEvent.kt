package org.fountainmc.api.event.world

import org.fountainmc.api.event.Cancellable
import org.fountainmc.api.world.Chunk

interface ChunkUnloadEvent : ChunkEvent, Cancellable {
    companion object {
        fun create(chunk: Chunk): ChunkUnloadEvent {
            class SimpleChunkUnloadEvent(override val chunk: Chunk): Cancellable, ChunkUnloadEvent {
                override var isCancelled = false
            }
            return SimpleChunkUnloadEvent(chunk)
        }
    }

}
