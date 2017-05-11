package org.fountainmc.api.event.world

import org.fountainmc.api.world.Chunk

interface ChunkLoadEvent : ChunkEvent {
    companion object {
        fun create(chunk: Chunk): ChunkLoadEvent {
            class SimpleChunkLoadEvent(override val chunk: Chunk): ChunkLoadEvent
            return SimpleChunkLoadEvent(chunk)
        }
    }
}
