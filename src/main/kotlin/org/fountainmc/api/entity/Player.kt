package org.fountainmc.api.entity

import org.fountainmc.api.chat.Component
import org.fountainmc.api.command.CommandSender
import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.PlayerData

/**
 * A connected player.
 */
interface Player : LivingEntity, PlayerData, CommandSender {
    /**
     * Send the specified chat component to this player.
     *
     * @param component the chat component to send
     */
    override fun sendMessage(component: Component<*>)

    /**
     * Send a chat message to the Player
     *
     * @param message the message to send
     */
    override fun sendMessage(message: String)

    /**
     * Send the specified list of messages to the Player,
     * as if by invoking [sendMessage] repeatedly.
     *
     * @param messages the messages to send
     */
    override fun sendMessages(vararg messages: String)

    /**
     * Hide an Entity from the Player's view.

     * @param entity Entity to hide
     */
    fun hide(entity: Entity)

    /**
     * Return if the player can see a specified entity

     * @param entity the entity to check if the player can see
     * *
     * @return if the player can see the specified entity
     */
    fun canSee(entity: Entity): Boolean

    val hiddenEntities: Set<Entity>

    /**
     * Return if the player is still connected to the server
     *
     * Returns false for NPCs

     * @return if connected
     */
    val isConnected: Boolean

    override val entityType: EntityType<Player>
        get() = EntityType.PLAYER

    /**
     * Copy all of the given data to this player.
     *
     * Doesn't copy passenger information.
     *
     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) = super<PlayerData>.copyDataFrom(data)

    /**
     * Take a snapshot of this player's data
     *
     * The resulting snapshot is thread-safe.

     * @return a snapshot
     */
    override fun snapshot(): PlayerData

}
