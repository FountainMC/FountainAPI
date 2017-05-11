package org.fountainmc.api.event.player

import org.fountainmc.api.entity.Player
import org.fountainmc.api.event.Cancellable

interface PlayerJoinEvent : PlayerEvent, Cancellable {
    companion object {
        fun create(player: Player): PlayerJoinEvent {
            class SimplePlayerJoinEvent(override val player: Player) : PlayerJoinEvent, Cancellable {
                override var isCancelled = false
            }
            return SimplePlayerJoinEvent(player)
        }
    }
}
