package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

@Nonnull
public interface Sand extends FallingBlock {

    SandType getType();

    Sand withType(SandType type);

    enum SandType {
        SAND,
        RED_SAND;
    }

}
