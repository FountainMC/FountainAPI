package org.fountainmc.api.world.block.plants;

import javax.annotation.Nonnegative;

public interface GrowingPlant extends Plant {
    @Nonnegative
    public default double getGrowthPercentage() {
        return getGrowth() / (double) getMaxGrowth();
    }

    // NOTE a utility wither for 'withGrowthPercentage' is left out here because plugins should explicitly handle getMaxGrowth()

    @Nonnegative
    public int getGrowth();

    /**
     * Get a copy of this plant with the given growth level
     *
     * @throws IllegalArgumentException if the growth level is negative or greater than {@link #getMaxGrowth()}
     * @param growth the growth level
     * @return the new plant
     */
    public GrowingPlant withGrowth(@Nonnegative int growth);

    /**
     * A utility method to get this type of plant's maximum growth height
     * <p>All block states of the same type must have the same maximum growth height.</p>
     *
     * @return the maximum growth height
     */
    @Nonnegative
    public int getMaxGrowth();

    public default boolean isFresh() {
        return getGrowth() == 0;
    }
}
