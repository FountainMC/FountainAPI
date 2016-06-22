package org.fountainmc.api.world.block.redstone;

import javax.annotation.Nonnegative;

public interface RedstoneWire extends PoweredBlock {

    @Override
    RedstoneWire withPower(@Nonnegative int power);

}
