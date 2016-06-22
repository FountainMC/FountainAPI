package org.fountainmc.api.world.block.redstone;

import javax.annotation.Nonnegative;

import org.fountainmc.api.world.block.BlockState;

public interface PoweredBlock extends BlockState {

    int MAXIMUM_POWER = 15;

    @Nonnegative
    int getPower();

    /**
     * Get a clone of this block with the given power
     *
     * @param power the new power
     * @return the new chest
     * @throws IllegalArgumentException if power is negative or greater than {@link #MAXIMUM_POWER}
     */
    PoweredBlock withPower(@Nonnegative int power);

}
