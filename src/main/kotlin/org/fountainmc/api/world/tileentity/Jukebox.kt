package org.fountainmc.api.world.tileentity

import org.fountainmc.api.inventory.item.Item
import org.fountainmc.api.world.block.JukeboxState

interface Jukebox : TileEntity<JukeboxState> {
    val record: Item?

    val hasRecord
        get() = record != null
}
