package org.fountainmc.api.event.world

import org.fountainmc.api.entity.Player
import org.fountainmc.api.event.Cancellable
import org.fountainmc.api.event.Event
import org.fountainmc.api.world.BlockPosition
import org.fountainmc.api.world.block.BlockState

interface BlockBreakEvent : Event, Cancellable {

    val position: BlockPosition

    val state: BlockState

    val player: Player

    companion object {
        fun create(player: Player, position: BlockPosition, state: BlockState): BlockBreakEvent {
            class SimpleBlockBreakEvent(
                    override val position: BlockPosition,
                    override val state: BlockState,
                    override val player: Player
            ) : Cancellable, BlockBreakEvent {
                init {
                    position.requireWorld(player)
                }
                override var isCancelled = false
            }
            return SimpleBlockBreakEvent(position, state, player)
        }
    }

}
