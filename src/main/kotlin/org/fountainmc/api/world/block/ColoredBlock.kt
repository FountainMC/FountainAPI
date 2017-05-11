package org.fountainmc.api.world.block

import org.fountainmc.api.Color

interface ColoredBlock : BlockState {

    val color: Color

    fun withColor(color: Color): ColoredBlock

}
