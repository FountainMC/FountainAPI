package org.fountainmc.api.world.block.redstone;

import javax.annotation.Nonnegative;

import org.fountainmc.api.world.block.BlockState;

public interface PoweredBlock extends BlockState {
    public static final int MAXIMUM_POWER = 15;

    @Nonnegative
    public int getPower();

    /**
     * Get a clone of this block with the given power
     *
     * @throws IllegalArgumentException if power is negative or greater than {@link #MAXIMUM_POWER}
     * @param power the new power
     * @return the new chest
     */
    public PoweredBlock withPower(@Nonnegative int power);
}
