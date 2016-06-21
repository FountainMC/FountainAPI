package org.fountainmc.api.event.world;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;
import org.fountainmc.api.entity.Player;
import org.fountainmc.api.event.AbstractCancellable;
import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.block.BlockState;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public interface BlockPlaceEvent extends Cancellable, Event {

    @Nonnull
    BlockState getNewBlockState();

    @Nonnull
    BlockState getOriginalBlockState();

    @Nonnull
    BlockPosition getPosition();

    @Nonnull
    Player getPlayer();

    static BlockPlaceEvent create(Player player, BlockPosition position, BlockState oldState, BlockState newState) {
        checkArgument(checkNotNull(player, "Null player").getWorld().equals(checkNotNull(position, "Null position").getWorld()),
                "Player's world %s doesn't match position's world %s", player.getWorld(), position.getWorld());
        checkNotNull(oldState, "oldState");
        checkNotNull(newState, "newState");
        class SimpleBlockPlaceEvent extends AbstractCancellable implements BlockPlaceEvent {

            @Nonnull
            @Override
            public BlockState getNewBlockState() {
                return newState;
            }

            @Nonnull
            @Override
            public BlockState getOriginalBlockState() {
                return oldState;
            }

            @Nonnull
            @Override
            public BlockPosition getPosition() {
                return position;
            }

            @Nonnull
            @Override
            public Player getPlayer() {
                return player;
            }
        }
        return new SimpleBlockPlaceEvent();
    }

}
