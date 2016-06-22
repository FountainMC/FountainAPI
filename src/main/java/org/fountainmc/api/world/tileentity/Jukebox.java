package org.fountainmc.api.world.tileentity;

import javax.annotation.Nullable;

import org.fountainmc.api.inventory.item.Item;
import org.fountainmc.api.world.block.JukeboxState;

public interface Jukebox extends TileEntity {
    @Nullable
    Item getRecord();

    default boolean hasRecord() {
        return getRecord() != null;
    }

    @Override
    default JukeboxState getBlockState() {
        return (JukeboxState) TileEntity.super.getBlockState();
    }
}
