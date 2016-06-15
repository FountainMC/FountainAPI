package org.fountainmc.api.world.block.plants;

import javax.annotation.Nonnegative;

public interface Sugarcane extends GrowingPlant {

    public static int MAXIMUM_GROWTH = 16;

    @Override
    public Sugarcane withGrowth(@Nonnegative int growth);

    @Override
    public default int getMaxGrowth() {
        return MAXIMUM_GROWTH;
    }

}
