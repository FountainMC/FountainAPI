package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.fountainmc.api.entity.Player;
import org.fountainmc.api.event.AbstractCancellable;
import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.block.BlockState;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public interface BlockBreakEvent extends Event, Cancellable {

    @Nonnull
    BlockPosition getPosition();

    @Nonnull
    BlockState getState();

    @Nonnull
    Player getPlayer();

    static BlockBreakEvent create(Player player, BlockPosition position, BlockState state) {
        checkArgument(checkNotNull(player, "Null player").getWorld().equals(checkNotNull(position, "Null position").getWorld()),
                "Player's world %s doesn't match position's world %s", player.getWorld(), position.getWorld());
        checkNotNull(state, "Null state");
        class SimpleBlockBreakEvent extends AbstractCancellable implements BlockBreakEvent {

            @Nonnull
            @Override
            public BlockPosition getPosition() {
                return position;
            }

            @Nonnull
            @Override
            public BlockState getState() {
                return state;
            }

            @Nonnull
            @Override
            public Player getPlayer() {
                return player;
            }
        }
        return new SimpleBlockBreakEvent();
    }

}
