package org.fountainmc.api.event.player;

import javax.annotation.ParametersAreNonnullByDefault;

import org.fountainmc.api.entity.Player;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public interface PlayerJoinedEvent extends PlayerEvent {

    static PlayerJoinedEvent create(Player player) {
        checkNotNull(player, "player");
        return () -> player;
    }

}
