package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

@Nonnull
public interface Sand extends FallingBlock {
    public SandType getType();

    public Sand withType(SandType type);

    public static enum SandType {
        SAND,
        RED_SAND;
    }
}
