package org.fountainmc.api.world.tileentity;

import javax.annotation.Nullable;

import org.fountainmc.api.inventory.item.Item;
import org.fountainmc.api.world.block.BlockState;
import org.fountainmc.api.world.block.JukeboxState;

public interface Jukebox extends TileEntity {
    @Nullable
    public Item getRecord();

    public default boolean hasRecord() {
        return getRecord() != null;
    }

    @Override
    public default JukeboxState getBlockState() {
        return (JukeboxState) TileEntity.super.getBlockState();
    }
}
