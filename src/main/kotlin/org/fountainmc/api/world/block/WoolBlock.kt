package org.fountainmc.api.world.block

import org.fountainmc.api.Color

interface WoolBlock : ColoredBlock {

    override fun withColor(color: Color): WoolBlock

}
