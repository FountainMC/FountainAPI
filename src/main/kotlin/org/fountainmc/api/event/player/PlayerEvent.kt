package org.fountainmc.api.event.player

import org.fountainmc.api.entity.Player
import org.fountainmc.api.event.Event

interface PlayerEvent : Event {

    val player: Player

}
