package org.fountainmc.api.event.world;

import org.fountainmc.api.world.Chunk;

public class ChunkUnloadEvent extends ChunkEvent {

    public ChunkUnloadEvent(Chunk chunk) {
        super(chunk);
    }
}
