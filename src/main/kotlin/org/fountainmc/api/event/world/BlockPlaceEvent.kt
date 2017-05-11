package org.fountainmc.api.event.world

import javax.annotation.ParametersAreNonnullByDefault

import org.fountainmc.api.entity.Player
import org.fountainmc.api.event.Cancellable
import org.fountainmc.api.event.Event
import org.fountainmc.api.world.BlockPosition
import org.fountainmc.api.world.block.BlockState

import com.google.common.base.Preconditions.*

@ParametersAreNonnullByDefault
interface BlockPlaceEvent : Cancellable, Event {

    val newBlockState: BlockState

    val originalBlockState: BlockState

    val position: BlockPosition

    val player: Player

    companion object {

        fun create(player: Player, position: BlockPosition, oldState: BlockState, newState: BlockState): BlockPlaceEvent {
            class SimpleBlockPlaceEvent(
                    override val newBlockState: BlockState,
                    override val originalBlockState: BlockState,
                    override val position: BlockPosition,
                    override val player: Player
            ) : Cancellable, BlockPlaceEvent {
                init {
                    position.requireWorld(player)
                }
                override var isCancelled = false
            }
            return SimpleBlockPlaceEvent(newState, oldState, position, player)
        }
    }

}
