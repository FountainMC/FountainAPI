package org.fountainmc.api.world.block.plants;

import javax.annotation.Nonnegative;

public interface Cactus extends GrowingPlant {

    int MAXIMUM_GROWTH = 16;

    @Override
    Cactus withGrowth(@Nonnegative int growth);

    @Override
    default int getMaxGrowth() {
        return MAXIMUM_GROWTH;
    }

}
