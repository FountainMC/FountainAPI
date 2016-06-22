package org.fountainmc.api.world.block.plants;

import javax.annotation.Nonnegative;

public interface GrowingPlant extends Plant {

    @Nonnegative
    default double getGrowthPercentage() {
        return getGrowth() / (double) getMaxGrowth();
    }

    // NOTE a utility wither for 'withGrowthPercentage' is left out here because
    // plugins should explicitly handle getMaxGrowth()

    @Nonnegative
    int getGrowth();

    /**
     * Get a copy of this plant with the given growth level
     *
     * @param growth the growth level
     * @return the new plant
     * @throws IllegalArgumentException if the growth level is negative or greater than {@link #getMaxGrowth()}
     */
    GrowingPlant withGrowth(@Nonnegative int growth);

    /**
     * A utility method to get this type of plant's maximum growth height
     * <p>All block states of the same type must have the same maximum growth height.</p>
     *
     * @return the maximum growth height
     */
    @Nonnegative
    int getMaxGrowth();

    default boolean isFresh() {
        return getGrowth() == 0;
    }

}
