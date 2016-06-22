package org.fountainmc.api.event.player;

import javax.annotation.Nonnull;

import org.fountainmc.api.entity.Player;
import org.fountainmc.api.event.Event;

public interface PlayerEvent extends Event {

    @Nonnull
    Player getPlayer();

}
