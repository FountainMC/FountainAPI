package org.fountainmc.api.event.world;

import org.fountainmc.api.world.Chunk;

public class ChunkLoadEvent extends ChunkEvent {

    public ChunkLoadEvent(Chunk chunk) {
        super(chunk);
    }

}
