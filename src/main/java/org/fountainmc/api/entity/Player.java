package org.fountainmc.api.entity;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableCollection;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.command.CommandSender;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.LivingEntityData;
import org.fountainmc.api.entity.data.MutableLivingEntityData;
import org.fountainmc.api.entity.data.MutablePlayerData;
import org.fountainmc.api.entity.data.PlayerData;

import static com.google.common.base.Preconditions.*;

/**
 * A connected player.
 */
@NonnullByDefault
public interface Player extends LivingEntity, MutablePlayerData, CommandSender {

    /**
     * Send a chat message to the Player
     *
     * @param message String to send
     */
    void sendMessage(String message);

    /**
     * Send multiple messages to the Player
     *
     * @param messages Strings to send
     */
    default void sendMessages(String... messages) {
        for (int i = 0; i < checkNotNull(messages, "Null messages array").length; i++) {
            String message = messages[i];
            checkNotNull(message, "Null message at index %s", i);
            sendMessage(message);
        }
    }

    /**
     * Hide an Entity from the Player's view.
     *
     * @param entity Entity to hide
     */
    void hide(Entity entity);

    /**
     * Return if the player can see a specified entity
     *
     * @param entity the entity to check if the player can see
     * @return if the player can see the specified entity
     */
    boolean canSee(Entity entity);

    ImmutableCollection<? extends Entity> getHiddenEntities();

    /**
     * Return if the player is still connected to the server
     * <p>Returns false for NPCs</p>
     *
     * @return if connected
     */
    boolean isConnected();

    @Override
    @Nonnull
    default EntityType<Player> getEntityType() {
        return EntityType.PLAYER;
    }

    /**
     * Copy all of the given data to this player.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        MutablePlayerData.super.copyDataFrom(data);
    }


    /**
     * Take a snapshot of this player's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    PlayerData snapshot();

}
