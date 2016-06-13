package org.fountainmc.api.event.world;

import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.Chunk;

public class ChunkEvent extends Event {

    private final Chunk chunk;

    public ChunkEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
