package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;

import org.fountainmc.api.event.AbstractCancellable;
import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.world.Chunk;

import static com.google.common.base.Preconditions.*;

public interface ChunkUnloadEvent extends ChunkEvent, Cancellable {

    @Nonnull
    public static ChunkUnloadEvent create(@Nonnull Chunk chunk) {
        checkNotNull(chunk, "Null chunk");
        class SimpleChunkUnloadEvent extends AbstractCancellable implements ChunkUnloadEvent {

            @Override
            @Nonnull
            public Chunk getChunk() {
                return chunk;
            }
        }
        return new SimpleChunkUnloadEvent();
    }

}
