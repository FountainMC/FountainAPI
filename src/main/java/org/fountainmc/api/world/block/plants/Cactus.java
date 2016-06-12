package org.fountainmc.api.world.block.plants;

import javax.annotation.Nonnegative;

public interface Cactus extends GrowingPlant {
    public static int MAXIMUM_GROWTH = 16;

    @Override
    public Cactus withGrowth(@Nonnegative int growth);

    @Override
    public default int getMaxGrowth() {
        return MAXIMUM_GROWTH;
    }
}
