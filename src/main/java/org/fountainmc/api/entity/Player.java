package org.fountainmc.api.entity;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import org.fountainmc.api.command.CommandSender;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A connected player.
 */
@ParametersAreNonnullByDefault
public interface Player extends LivingEntity, CommandSender {

    /**
     * Get the name of the Player.
     *
     * @return name of the Player
     */
    @Nonnull
    String getName();

    /**
     * Get the Unique Identifier of the Player.
     *
     * @return UUID of the Player
     */
    @Nonnull
    UUID getUUID();

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
    public boolean canSee(Entity entity);

    public ImmutableCollection<? extends Entity> getHiddenEntities();

    /**
     * Return if the player is still connected to the server
     * <p>Returns false for NPCs</p>
     *
     * @return if connected
     */
    public boolean isConnected();

    @Override
    default EntityType<Player> getEntityType() {
        return EntityType.PLAYER;
    }

}
