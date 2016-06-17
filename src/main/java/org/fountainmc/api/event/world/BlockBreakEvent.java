package org.fountainmc.api.event.world;

import org.fountainmc.api.entity.Player;
import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.block.BlockState;

public interface BlockBreakEvent extends Event, Cancellable {

    BlockPosition getPosition();

    BlockState getState();

    Player getPlayer();

}
