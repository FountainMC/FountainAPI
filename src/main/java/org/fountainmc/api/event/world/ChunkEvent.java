package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;

import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.Chunk;

public interface ChunkEvent extends Event {

    @Nonnull
    Chunk getChunk();

}
