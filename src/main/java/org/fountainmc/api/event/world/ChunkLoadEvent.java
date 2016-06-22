package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;

import org.fountainmc.api.world.Chunk;

import static com.google.common.base.Preconditions.checkNotNull;

public interface ChunkLoadEvent extends ChunkEvent {

    @Nonnull
    static ChunkLoadEvent create(@Nonnull Chunk chunk) {
        checkNotNull(chunk, "Null chunk");
        return () -> chunk;
    }

}
