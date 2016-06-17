package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.fountainmc.api.world.Chunk;

import static com.google.common.base.Preconditions.*;

public interface ChunkLoadEvent extends ChunkEvent {
    @Nonnull
    public static ChunkLoadEvent create(@Nonnull Chunk chunk) {
        checkNotNull(chunk, "Null chunk");
        return () -> chunk;
    }
}
