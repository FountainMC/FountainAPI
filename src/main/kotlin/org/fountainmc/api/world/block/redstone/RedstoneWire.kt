package org.fountainmc.api.world.block.redstone

import javax.annotation.Nonnegative

interface RedstoneWire : PoweredBlock {

    override fun withPower(@Nonnegative power: Int): RedstoneWire

}
